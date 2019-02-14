package myMonitor.DesktopScreen;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Calendar;
import javax.imageio.ImageIO;

public class RemoteDesktopScreen {
	/*
	 * ��ȡ��ͼ�ļ�����ʱ������
	 */
	public String getFilename() {
		String fileName;
		Calendar cal=Calendar.getInstance(); 
		//int y=cal.get(Calendar.YEAR); ��
		int m=cal.get(Calendar.MONTH)+1; //��
		int d=cal.get(Calendar.DATE); //��
		int h=cal.get(Calendar.HOUR_OF_DAY);//ʱ 
		int mi=cal.get(Calendar.MINUTE); //��
		int s=cal.get(Calendar.SECOND);//��
		
		fileName=m+"-"+d+"-"+h+"-"+mi+"-"+s+".png";
		return fileName;
		
	}
	/*
	 *    ��ͼ ������
	 *  @param folder  ͼƬ�����·��
	 *  @param fileName ͼƬ���������
	 */
	public void desktopScreen(String folder,String fileName)throws Exception{
		//�����Ļ��С������һ��Rectangle(�� ��)
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//��ȡ��Ļ���
		Rectangle screenRectangle = new Rectangle(screenSize);
		//������������Ļ�ж�ȡ�����ص�ͼ��
		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		//����·��
		File screenFile = new File(folder);
		if(!screenFile.exists()) {
			screenFile.mkdir();//·�������ڣ�����
		}
		File f = new File(screenFile,fileName);
		//������fΪ�ļ�����ͼ��1��.png��ʽд���ļ�f
		ImageIO.write(image, "png", f);	
	}
	/*
	 * �ѽ�ͼ���뵽�����ѷ�����
	 */
	/*public static BufferedImage outputImage(String imgPath) throws FileNotFoundException, IOException {
		
        
        BufferedImage image = ImageIO.read(new FileInputStream(imgPath));
        ImageIO.write(image, "png", response.getOutputStream());
        System.out.println(image);
		return image;
	}*/
}
