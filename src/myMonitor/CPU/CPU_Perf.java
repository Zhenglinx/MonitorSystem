package myMonitor.CPU;
import java.text.DecimalFormat;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CPU_Perf {
	public static JSONArray cpu() throws SigarException {
		DecimalFormat df=new DecimalFormat("0.0");//控制小数点后位数,这里控制为小数点后3位
		Sigar sigar=new Sigar();
		CpuInfo infos[]=sigar.getCpuInfoList();//获取CPU信息
		CpuPerc cpuList[]=null;
		cpuList=sigar.getCpuPercList();//获取CPU使用性能
		JSONArray CpuArr=new JSONArray();//json数组，因为cpu有多个，一个json对象存储一个cpu
		JSONObject CpuData ;//json对象，用于存储一个cpu的使用情况
		for(int i=0;i<infos.length;i++) {//一台电脑可能不止一块CPU
			//方法format将数据格式化
//			System.out.println("the "+(i+1)+" CPU");
//			System.out.println("CPU用户使用率:    " + CpuPerc.format(cpuList[i].getUser()));// 用户使用率
//	        System.out.println("CPU系统使用率:    " + CpuPerc.format(cpuList[i].getSys()));// 系统使用率
//	        System.out.println("CPU当前空闲率:    " + CpuPerc.format(cpuList[i].getIdle()));// 当前空闲率
//	        System.out.println("CPU总的使用率:    " + CpuPerc.format(cpuList[i].getCombined()));// 总的使用率
			CpuData= new JSONObject();//调用super（）
			CpuData.put("Num", i+1);//cpu块号
			CpuData.put("Idle",df.format(cpuList[i].getIdle()*100));//cpu空闲率
			CpuData.put("Combind",df.format(cpuList[i].getCombined()*100));//cpu使用率
			
			CpuArr.add(CpuData);
		}
		//System.out.println(CpuArr.toString());
		return CpuArr;
	}

}


