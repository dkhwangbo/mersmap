package mersmap;

import java.io.IOException;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class readData {

	JTable table1;
	JTable table2;
	JTable table3;
	JTable table4;
	
	DefaultTableModel model1;
	DefaultTableModel model2;
	DefaultTableModel model3;
	DefaultTableModel model4;
	
	public JTable getTable1(){
		return table1;
	}
	
	public JTable getTable2(){
		return table2;
	}
	
	public JTable getTable3(){
		return table3;
	}
	
	public JTable getTable4(){
		return table4;
	}
	
	public DefaultTableModel getModel1(){
		return model1;
	}
	
	public DefaultTableModel getModel2(){
		return model2;
	}
	
	public DefaultTableModel getModel3(){
		return model3;
	}
	
	public DefaultTableModel getModel4(){
		return model4;
	}
	
	public readData() throws IOException{
		String url = "http://newstapa.org/25904";
		Document doc = Jsoup.connect(url)
						// 	.data("query", "Java")
							.userAgent("Mozilla")
							.cookie("auth", "token")
						//	.timeout(3000)
							.post();
  
		Iterator<Element> itr = doc.select("table").iterator();
		String domain[] = {"����","�λ�","�뱸","��õ","����","����","���",
					"����","���","����","���","�泲","����","����","���","�泲","����"
					};	
 
		// "ǥ1. �޸��� Ȯ��ȯ�� �߻�����"�� ���� ó��

		//System.out.println("ǥ1. �޸��� Ȯ��ȯ�� �߻�����");
		
		Iterator<Element> _itr = itr.next().select("tr").iterator();
		String temp = new String();
		boolean isSame = false;
		_itr.next();

		model1= new DefaultTableModel();
		
		model1.addColumn("����");
		model1.addColumn("������");
		model1.addColumn("ȯ��");
		
		while(_itr.hasNext()){
			Element t= _itr.next().tagName("td");
			Elements _t = t.getAllElements();
	  
			for(int i = 0; i<domain.length ; i++){
				if(_t.get(1).text().equals(domain[i])){
					temp = _t.get(1).text();
					isSame = true;
					break;
				}
			}
	  
			if(isSame == true){
				//System.out.println(temp + " " + _t.get(2).text() + " " + _t.get(_t.size()-1).text());
				model1.addRow(new Object[]{temp, _t.get(2).text(), _t.get(_t.size()-1).text()});
				isSame = false;
			}
			else{
				if(!_t.get(1).text().equals("�������� ������"))
				//System.out.println(temp + " " + _t.get(1).text() + " " + _t.get(_t.size()-1).text());
				model1.addRow(new Object[]{temp, _t.get(1).text(), _t.get(_t.size()-1).text()});
			} 
		}
		
		table1 = new JTable(model1);
  
  
		// "ǥ2.�޸��� Ȯ��ȯ�� ���� ����"�� ���� ó��
  
		//System.out.println("\nǥ2. �޸��� Ȯ��ȯ�� �߻�����");
		
		_itr = itr.next().select("tr").iterator();
		_itr.next();
		
		model2= new DefaultTableModel();
		
		model2.addColumn("����");
		model2.addColumn("������");
		
		while(_itr.hasNext()){
			Element t= _itr.next().tagName("td");
			Elements _t = t.getAllElements();
			
			for(int i = 0; i<domain.length ; i++){
				if(_t.get(1).text().equals(domain[i])){
					temp = _t.get(1).text();
					isSame = true;
					break;
				}
			}
			  
			if(isSame == true){
				//System.out.println(temp + " " + _t.get(2).text());
				model2.addRow(new Object[]{temp, _t.get(2).text()});
				isSame = false;
			}
			else{
				if(!_t.get(1).text().equals("�������� ������")){
					//System.out.println(temp + " " + _t.get(1).text());
					model2.addRow(new Object[]{temp, _t.get(1).text()});
				}
			} 
		}
		
		table2 = new JTable(model2);

		// "ǥ3.ȯ�ں� ����"�� ���� ó��
  
		//System.out.println("\nǥ3. ȯ�ں� ����");
		  
		_itr = itr.next().select("tr").iterator();
		_itr.next();
		
		model3= new DefaultTableModel();
		
		model3.addColumn("����");
		model3.addColumn("��������");
		model3.addColumn("Ȯ����");
		model3.addColumn("����");
		
		while(_itr.hasNext()){
			Element t= _itr.next().tagName("td");
			Elements _t = t.getAllElements();
			  
			//System.out.println(_t.get(1).text() + " " + _t.get(2).text() + " " + _t.get(3).text() + " " + _t.get(4).text());
			model3.addRow(new Object[]{_t.get(1).text(), _t.get(2).text(), _t.get(3).text(), _t.get(4).text()});
		}
		
		table3 = new JTable(model3);
  
		// "ǥ4. �������� �Կ�ġ��(�ݸ�) ���� � ����
 
		//System.out.println("\nǥ4. �������� �Կ�ġ��(�ݸ�) ���� � ����");
		  
		_itr = itr.next().select("tr").iterator();
		_itr.next();
		
		model4 = new DefaultTableModel();
		
		model4.addColumn("����");
		model4.addColumn("������");
		model4.addColumn("���к����");
		model4.addColumn("�ּ�");
		
		while(_itr.hasNext()){
			Element t= _itr.next().tagName("td");
			Elements _t = t.getAllElements();
			  
			//System.out.println(_t.get(1).text() + " " + _t.get(2).text() + " " + _t.get(3).text() + " " + _t.get(4).text());
			model4.addRow(new Object[]{_t.get(1).text(), _t.get(2).text(), _t.get(3).text(), _t.get(4).text()});
			/*
			if(_t.get(1).text().contains("��")){
				temp = _t.get(1).text();
				System.out.println(temp + " " + _t.get(2).text() + " " + _t.get(3).text() + " " + _t.get(4).text());
			}
			else{
				System.out.println(temp + " " + _t.get(1).text() + " " + _t.get(2).text() + " " + _t.get(3).text());
			}
			*/
		}
		table4 = new JTable(model4);
	}
//	public static void main(String[] args) throws Exception { 
//	
//		readData r = new readData();

//		String url = "http://newstapa.org/25904";
//		Document doc = Jsoup.connect(url)
//						// 	.data("query", "Java")
//							.userAgent("Mozilla")
//							.cookie("auth", "token")
//						//	.timeout(3000)
//							.post();
//  
//		Iterator<Element> itr = doc.select("table").iterator();
//		String domain[] = {"����","�λ�","�뱸","��õ","����","����","���",
//					"����","���","����","���","�泲","����","����","���","�泲","����"
//					};	
// 
//		// "ǥ1. �޸��� Ȯ��ȯ�� �߻�����"�� ���� ó��
//
//		System.out.println("ǥ1. �޸��� Ȯ��ȯ�� �߻�����");
//		
//		Iterator<Element> _itr = itr.next().select("tr").iterator();
//		String temp = new String();
//		boolean isSame = false;
//		_itr.next();
//		String t1_column[] = {"����", "������", "ȯ��"};
//		table1 = new JTable();
//		t1.setColumnModel(new TableColumnModel("����"));
//		while(_itr.hasNext()){
//			Element t= _itr.next().tagName("td");
//			Elements _t = t.getAllElements();
//	  
//			for(int i = 0; i<domain.length ; i++){
//				if(_t.get(1).text().equals(domain[i])){
//					temp = _t.get(1).text();
//					isSame = true;
//					break;
//				}
//			}
//	  
//			if(isSame == true){
//				System.out.println(temp + " " + _t.get(2).text() + " " + _t.get(_t.size()-1).text());
//				
//				isSame = false;
//			}
//			else{
//				if(!_t.get(1).text().equals("�������� ������"))
//				System.out.println(temp + " " + _t.get(1).text() + " " + _t.get(_t.size()-1).text());
//			} 
//		}
//  
//  
//		// "ǥ2.�޸��� Ȯ��ȯ�� ���� ����"�� ���� ó��
//  
//		System.out.println("\nǥ2. �޸��� Ȯ��ȯ�� �߻�����");
//		  
//		_itr = itr.next().select("tr").iterator();
//		_itr.next();
//		while(_itr.hasNext()){
//			Element t= _itr.next().tagName("td");
//			Elements _t = t.getAllElements();
//			
//			for(int i = 0; i<domain.length ; i++){
//				if(_t.get(1).text().equals(domain[i])){
//					temp = _t.get(1).text();
//					isSame = true;
//					break;
//				}
//			}
//			  
//			if(isSame == true){
//				System.out.println(temp + " " + _t.get(2).text());
//				isSame = false;
//			}
//			else{
//				if(!_t.get(1).text().equals("�������� ������"))
//				System.out.println(temp + " " + _t.get(1).text());
//			} 
//		}
//
//		// "ǥ3.ȯ�ں� ����"�� ���� ó��
//  
//		System.out.println("\nǥ3. ȯ�ں� ����");
//		  
//		_itr = itr.next().select("tr").iterator();
//		_itr.next();
//		while(_itr.hasNext()){
//			Element t= _itr.next().tagName("td");
//			Elements _t = t.getAllElements();
//			  
//			System.out.println(_t.get(1).text() + " " + _t.get(2).text() + " " + _t.get(3).text() + " " + _t.get(4).text());
//		}
//  
//		// "ǥ4. �������� �Կ�ġ��(�ݸ�) ���� � ����
// 
//		System.out.println("\nǥ4. �������� �Կ�ġ��(�ݸ�) ���� � ����");
//		  
//		_itr = itr.next().select("tr").iterator();
//		_itr.next();
//		while(_itr.hasNext()){
//			Element t= _itr.next().tagName("td");
//			Elements _t = t.getAllElements();
//			  
//			System.out.println(_t.get(1).text() + " " + _t.get(2).text() + " " + _t.get(3).text() + " " + _t.get(4).text());
//			/*
//			if(_t.get(1).text().contains("��")){
//				temp = _t.get(1).text();
//				System.out.println(temp + " " + _t.get(2).text() + " " + _t.get(3).text() + " " + _t.get(4).text());
//			}
//			else{
//				System.out.println(temp + " " + _t.get(1).text() + " " + _t.get(2).text() + " " + _t.get(3).text());
//			}
//			*/
//		}
		
//	}
} 




