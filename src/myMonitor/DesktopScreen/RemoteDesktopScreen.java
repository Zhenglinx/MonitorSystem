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
	 * 获取截图文件名，时间命名
	 */
	public String getFilename() {
		String fileName;
		Calendar cal=Calendar.getInstance(); 
		//int y=cal.get(Calendar.YEAR); 年
		int m=cal.get(Calendar.MONTH)+1; //月
		int d=cal.get(Calendar.DATE); //日
		int h=cal.get(Calendar.HOUR_OF_DAY);//时 
		int mi=cal.get(Calendar.MINUTE); //分
		int s=cal.get(Calendar.SECOND);//秒
		
		fileName=m+"-"+d+"-"+h+"-"+mi+"-"+s+".png";
		return fileName;
		
	}
	/*
	 *    截图 并保存
	 *  @param folder  图片保存的路径
	 *  @param fileName 图片保存的名字
	 */
	public void desktopScreen(String folder,String fileName)throws Exception{
		//获得屏幕大小并创建一个Rectangle(区 域)
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕宽高
		Rectangle screenRectangle = new Rectangle(screenSize);
		//创建包含从屏幕中读取的像素的图像
		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		//保存路径
		File screenFile = new File(folder);
		if(!screenFile.exists()) {
			screenFile.mkdir();//路径不存在，创建
		}
		File f = new File(screenFile,fileName);
		//决定了f为文件，将图像1以.png格式写入文件f
		ImageIO.write(image, "png", f);	
	}
	/*
	 * 把截图输入到流（已废弃）
	 */
	/*public static BufferedImage outputImage(String imgPath) throws FileNotFoundException, IOException {
		
        
        BufferedImage image = ImageIO.read(new FileInputStream(imgPath));
        ImageIO.write(image, "png", response.getOutputStream());
        System.out.println(image);
		return image;
	}*/
}
