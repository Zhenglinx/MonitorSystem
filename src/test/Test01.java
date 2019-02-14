package test;

public class Test01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath="E:\\aa2\\wq.txt";
		String savePath="D:\\Zip"+filePath.substring(filePath.lastIndexOf("\\"), filePath.lastIndexOf("."))+".zip";
		System.out.println(savePath);
	}

}
