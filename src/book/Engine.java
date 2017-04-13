package book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Engine {
	
	static Scanner scan = new Scanner(System.in);
	static String filePath = "c:\\test\\book.txt";
	BufferedReader br;

	public void printMenu() {
		 
		System.out.println("도서 관리 프로그램입니다.");
		System.out.println("1. 도서 목록 ");
		System.out.println("2. 도서 검색");
		System.out.println("3. 신규 도서 추가");
		System.out.println("4. 도서 삭제");
		System.out.println("0. 프러그램 종료");
	}
	
	public int inputChoice() {
		int choice = -1;

		System.out.println("메뉴를 선택하세요 : ");
		choice = scan.nextInt();
		scan.nextLine();
		
		System.out.println();
		
		return choice;
	}


	public void deleteBook() throws IOException{
		String tmpFilePath = filePath + "tmp";
		int count = 1;
		System.out.println("삭제할 책 번호를 입력해주세요 :  ");
		int deleteLineNumber = scan.nextInt();
		System.out.println("책 번호:" + deleteLineNumber);
		
		BufferedReader br = new BufferedReader (new FileReader(filePath));
		BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFilePath));
		String str = "";
		
		while ((str = br.readLine()) != null){
			
			if(count != deleteLineNumber){
				bw.write(str);
				bw.write("\r\n");
			}
			count++;
		}
	bw.close();
	br.close();

	FileInputStream fis = new FileInputStream(tmpFilePath);
	FileOutputStream fos = new FileOutputStream(filePath);
	
	int data = 0;
	while ((data = fis.read()) != -1){
		fos.write(data);
	}
	
	fis.close();
	fos.close();
	
	File f = new File(tmpFilePath);
	f.deleteOnExit();
	
	}

	public void insertBook() throws IOException {

		Book b = new Book();
		BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
		

		System.out.println("추가할 책의 정보를 입력해주세요 : ");

		scan.nextLine();

		System.out.println("책 이름 : ");
		b.setName(scan.nextLine());

		System.out.println("저 자 : ");
		b.setAuthor(scan.nextLine());

		System.out.println("출판사 : ");
		b.setPublisher(scan.nextLine());

		System.out.println("가 격 :");
		b.setCost(scan.nextLine());
		System.out.println("입력값(책이름 -저자 -출판사-가격)");
		System.out.println(b.toString());
		
		bw.write(b.getName() + "\t" + b.getAuthor() + "\t" + b.getPublisher() + "\t" + b.getCost());
		bw.newLine();

		bw.close();
	}

	public void searchBook() throws FileNotFoundException {
		System.out.println("검색할 키워드를 입력하세요 : ");
		

		String keyword = scan.nextLine();

		String str = "";
		br = new BufferedReader(new FileReader(filePath));
			
		try {
			while ((str = br.readLine()) != null) {
							
				if(str.contains(keyword)){
					System.out.println("책이름 \t저자 \t출판사 \t가격");
					System.out.println(str);
				}	
}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void printBook() throws FileNotFoundException {
		int count = 1;
		System.out.println("책 출력");
		br = new BufferedReader(new FileReader(filePath));
		
		String str = "";
		try {
			while ((str = br.readLine()) != null) {
				System.out.println(count +"> "+str);
				count++;
			}
			br.close();
			
		} catch (IOException e) {
			System.out.println("책 정보를 읽어 올 수 없습니다.");
			System.out.println("저장한 파일을 찾을 수없습니다.");
			e.printStackTrace();
		}
	}
}

