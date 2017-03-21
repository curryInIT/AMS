package cn.scu.ams.daoImp;

import cn.scu.ams.dao.SendMailDao;
import cn.scu.ams.utils.MailMessage;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: jingjing
 * Date: 17-3-16
 * Time: ����10:04
 * To change this template use File | Settings | File Templates.
 */
public class SendMailDaoImp implements SendMailDao{
    public static final int QQ=0;
    public static final int WANGYI163=1;

    private Socket socket =null;
    private BufferedReader input = null;
    private BufferedWriter output = null;
    private String server = null;
    private int port;
    private MailMessage message;

    public MailMessage getMessage(){
        return message;
    }
    public SendMailDaoImp setMessage(MailMessage message){
        this.message = message;
        return this;
    }
    public Socket getSocket(){
        return socket;
    }
    public void setSocket(Socket socket){
        this.socket = socket;
    }
    public BufferedReader getInput(){
        return input;
    }
    public void setInput(BufferedReader input){
        this.input = input;
    }
    public BufferedWriter getOutput(){
        return output;
    }
    public void setOutput(BufferedWriter output){
        this.output = output;
    }
    public String getServer(){
        return server;
    }
    public void setServer(String server){
        this.server = server;
    }
    public int getPort(){
        return port;
    }
    public void setPort(int port){
        this.port = port;
    }

    public static SendMailDaoImp getInstance(int sendMailType){
        SendMailDaoImp mailSend = new SendMailDaoImp();
        switch (sendMailType){
            case QQ:
                mailSend.setServer("smtp.qq.com");
                mailSend.setPort(25);
                break;
            case WANGYI163:
                mailSend.setServer("smtp.163.com");
                mailSend.setPort(25) ;
                break;
            default:
                break;

        }
        try {
            // �뷢���ʼ�����������Socket����,���õ����������
            Socket socket = new Socket(mailSend.getServer(), mailSend
                    .getPort());
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream()));

            mailSend.setSocket(socket);
            mailSend.setInput(input);
            mailSend.setOutput(output);
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return mailSend;
    }

    public int sendToServer(String order) {
        try {
            this.getOutput().write(order);
            this.getOutput().newLine();
            this.getOutput().flush();

            System.out.println("�ѷ�������:" + order);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return getResult();
    }

    public int getResult() {
        String line = "";
        try {
            line = this.getInput().readLine();
            System.out.println("����������״̬��" + line);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // �ӷ�����������Ϣ��ȡ��״̬��,��ת������������
        StringTokenizer get = new StringTokenizer(line, " ");
        return Integer.parseInt(get.nextToken());
    }

    public void regist() throws IOException {
        int result = getResult();
        // �����Ϸ������󣬷���������220Ӧ��
        if (result != 220) {
            throw new IOException("���ӷ�����ʧ��");
        }

        result = sendToServer("HELO " + this.getServer());
        // HELO����ɹ��󣬷���������250Ӧ��
        if (result != 250) {
            throw new IOException("ע���ʼ�������ʧ��");
        }
    }

    public void login() throws IOException {
        BASE64Encoder encode = new BASE64Encoder();

        int result = sendToServer("AUTH LOGIN");
        // ��½����ɹ�������������334Ӧ��
        if (result != 334)
            throw new IOException("��½����ʧ��");

        // �������û���������֤
        result = sendToServer(encode.encode(this.message.getUser().getBytes()));
        if (result != 334)
            throw new IOException("�û�������");

        // ���������������֤
        result = sendToServer(encode.encode(message.getPassword().getBytes()));
        if (result != 235)
            throw new IOException("�û���֤����");
    }

    public void setMailFrom() throws IOException {
        int result = sendToServer("MAIL FROM:<" + this.getMessage().getFrom()
                + ">");
        if (result != 250)
            throw new IOException("�ʼ�Դ��ַ����");
    }

    public void setMailTo() throws IOException {
        int result = sendToServer("RCPT TO:<" + this.getMessage().getTo() + ">");
        if (result != 250)
            throw new IOException("�ʼ�Ŀ�ĵ�ַ����");
    }

    public void setData() throws IOException {
        int result = sendToServer("DATA");
        // ����date�س���,���յ�354Ӧ���,���������ʼ�����
        if (result != 354)
            throw new IOException("���ܷ�������");

        this.getOutput().write("FROM:" + this.getMessage().getDatafrom());
        this.getOutput().newLine();
        this.getOutput().write("TO:" + this.getMessage().getDatato());
        this.getOutput().newLine();
        this.getOutput().write("SUBJECT:" + this.getMessage().getSubject());
        this.getOutput().newLine();
        this.getOutput().newLine();
        this.getOutput().write(this.getMessage().getContent());
        this.getOutput().newLine();

        // ���ӻس������ʼ���������
        result = sendToServer(".");
        if (result != 250)
            throw new IOException("�������ݴ���");
    }

    public void quit() throws IOException {
        int result = sendToServer("QUIT");
        if (result != 221)
            throw new IOException("δ����ȷ�˳�");

        this.getInput().close();
        this.getOutput().close();
    }

    public boolean sendMail() {
        boolean success = true;
        try {
            // ע�ᵽ�����ʼ�������
            regist();
            // ��½��������
            login();
            // �����ʼ�Դ��ַ
            setMailFrom();
            // �����ʼ�Ŀ�ĵ�ַ
            setMailTo();
            // �����ʼ���
            setData();
            // �˳��ʼ�
            quit();
        }
        catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }
}
