package com.ohgiraffers.section01.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        int port = 8500;

        ServerSocket server =new ServerSocket(port);

        while(true) {

            Socket client = server.accept();   // client를 서버에 연결 허용해준다!

            InputStream input = client.getInputStream();
            OutputStream output = client.getOutputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            PrintWriter pw = new PrintWriter(output);

            while (true) {

                String msg = br.readLine();     //client 가 보낸 메세지를 읽을 것임!!

                if(!msg.equals("exit")) {
                    System.out.println(client.getInetAddress().getHostAddress() + "가 보낸 메세지" +  msg);  // ip주소
                    pw.println("메세지 받기 성공!!!");
                    pw.flush();

                } else {
                    System.out.println("접속 종료");
                    break;

                }

            }

            br.close();
            pw.close();
            client.close();

        }

    }

}
