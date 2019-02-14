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
		DecimalFormat df=new DecimalFormat("0.0");//����С�����λ��,�������ΪС�����3λ
		Sigar sigar=new Sigar();
		CpuInfo infos[]=sigar.getCpuInfoList();//��ȡCPU��Ϣ
		CpuPerc cpuList[]=null;
		cpuList=sigar.getCpuPercList();//��ȡCPUʹ������
		JSONArray CpuArr=new JSONArray();//json���飬��Ϊcpu�ж����һ��json����洢һ��cpu
		JSONObject CpuData ;//json�������ڴ洢һ��cpu��ʹ�����
		for(int i=0;i<infos.length;i++) {//һ̨���Կ��ܲ�ֹһ��CPU
			//����format�����ݸ�ʽ��
//			System.out.println("the "+(i+1)+" CPU");
//			System.out.println("CPU�û�ʹ����:    " + CpuPerc.format(cpuList[i].getUser()));// �û�ʹ����
//	        System.out.println("CPUϵͳʹ����:    " + CpuPerc.format(cpuList[i].getSys()));// ϵͳʹ����
//	        System.out.println("CPU��ǰ������:    " + CpuPerc.format(cpuList[i].getIdle()));// ��ǰ������
//	        System.out.println("CPU�ܵ�ʹ����:    " + CpuPerc.format(cpuList[i].getCombined()));// �ܵ�ʹ����
			CpuData= new JSONObject();//����super����
			CpuData.put("Num", i+1);//cpu���
			CpuData.put("Idle",df.format(cpuList[i].getIdle()*100));//cpu������
			CpuData.put("Combind",df.format(cpuList[i].getCombined()*100));//cpuʹ����
			
			CpuArr.add(CpuData);
		}
		//System.out.println(CpuArr.toString());
		return CpuArr;
	}

}


