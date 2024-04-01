package com.ohgiraffers.section01.tcp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        int port = 8500;        // 어떤 포트에서 연결할 건지 기술

        String serverIP = "115.95.149.11";       // *다른 사람의 ip 주소(연결)

        // 전화기 만들기~~

        Socket socket = new Socket(serverIP, port); // serverIP랑 port 번호 같이 넘겨줄 것임! >add method signiture로 오류해결하기(try&catch 아님!)

        if(socket != null) {

            InputStream input = socket.getInputStream();    // 메세지 입력할것임!
            OutputStream output = socket.getOutputStream(); // 내보내기도 할 것임!!

            BufferedReader br = new BufferedReader(new InputStreamReader(input));   // 보조스트림도 만들기
            PrintWriter pw = new PrintWriter(output);

            Scanner sc = new Scanner(System.in);

            do {

                System.out.println("메세지 입력 : ");
                String msg = sc.nextLine();

                pw.println(msg);
                pw.flush();     // 남은 자원이 있는지 마지막까지 밀어주기~~

                if(msg.equals("exit")) {        // exit이라고 하면 대화종료시키기!
                    break;
                }

                String recieveMessage = br.readLine();
                System.out.println(recieveMessage);

            } while (true);

            // 마지막으로 사용한 것들은 다 닫아주기!!!
            br.close();
            pw.close();
            socket.close();
        }

    }

}
