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
		String domain[] = {"서울","부산","대구","인천","광주","대전","울산",
					"세종","경기","강원","충북","충남","전북","전남","경북","경남","제주"
					};	
 
		// "표1. 메르스 확진환자 발생병원"에 대한 처리

		//System.out.println("표1. 메르스 확진환자 발생병원");
		
		Iterator<Element> _itr = itr.next().select("tr").iterator();
		String temp = new String();
		boolean isSame = false;
		_itr.next();

		model1= new DefaultTableModel();
		
		model1.addColumn("지역");
		model1.addColumn("병원명");
		model1.addColumn("환자");
		
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
				if(!_t.get(1).text().equals("역학조사 진행중"))
				//System.out.println(temp + " " + _t.get(1).text() + " " + _t.get(_t.size()-1).text());
				model1.addRow(new Object[]{temp, _t.get(1).text(), _t.get(_t.size()-1).text()});
			} 
		}
		
		table1 = new JTable(model1);
  
  
		// "표2.메르스 확진환자 경유 병원"에 대한 처리
  
		//System.out.println("\n표2. 메르스 확진환자 발생병원");
		
		_itr = itr.next().select("tr").iterator();
		_itr.next();
		
		model2= new DefaultTableModel();
		
		model2.addColumn("지역");
		model2.addColumn("병원명");
		
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
				if(!_t.get(1).text().equals("역학조사 진행중")){
					//System.out.println(temp + " " + _t.get(1).text());
					model2.addRow(new Object[]{temp, _t.get(1).text()});
				}
			} 
		}
		
		table2 = new JTable(model2);

		// "표3.환자별 정보"에 대한 처리
  
		//System.out.println("\n표3. 환자별 정보");
		  
		_itr = itr.next().select("tr").iterator();
		_itr.next();
		
		model3= new DefaultTableModel();
		
		model3.addColumn("연번");
		model3.addColumn("인적사항");
		model3.addColumn("확진일");
		model3.addColumn("개요");
		
		while(_itr.hasNext()){
			Element t= _itr.next().tagName("td");
			Elements _t = t.getAllElements();
			  
			//System.out.println(_t.get(1).text() + " " + _t.get(2).text() + " " + _t.get(3).text() + " " + _t.get(4).text());
			model3.addRow(new Object[]{_t.get(1).text(), _t.get(2).text(), _t.get(3).text(), _t.get(4).text()});
		}
		
		table3 = new JTable(model3);
  
		// "표4. 국가지정 입원치료(격리) 병상 운영 병원
 
		//System.out.println("\n표4. 국가지정 입원치료(격리) 병상 운영 병원");
		  
		_itr = itr.next().select("tr").iterator();
		_itr.next();
		
		model4 = new DefaultTableModel();
		
		model4.addColumn("순번");
		model4.addColumn("병원명");
		model4.addColumn("음압병상수");
		model4.addColumn("주소");
		
		while(_itr.hasNext()){
			Element t= _itr.next().tagName("td");
			Elements _t = t.getAllElements();
			  
			//System.out.println(_t.get(1).text() + " " + _t.get(2).text() + " " + _t.get(3).text() + " " + _t.get(4).text());
			model4.addRow(new Object[]{_t.get(1).text(), _t.get(2).text(), _t.get(3).text(), _t.get(4).text()});
			/*
			if(_t.get(1).text().contains("번")){
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
//		String domain[] = {"서울","부산","대구","인천","광주","대전","울산",
//					"세종","경기","강원","충북","충남","전북","전남","경북","경남","제주"
//					};	
// 
//		// "표1. 메르스 확진환자 발생병원"에 대한 처리
//
//		System.out.println("표1. 메르스 확진환자 발생병원");
//		
//		Iterator<Element> _itr = itr.next().select("tr").iterator();
//		String temp = new String();
//		boolean isSame = false;
//		_itr.next();
//		String t1_column[] = {"지역", "병원명", "환자"};
//		table1 = new JTable();
//		t1.setColumnModel(new TableColumnModel("지역"));
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
//				if(!_t.get(1).text().equals("역학조사 진행중"))
//				System.out.println(temp + " " + _t.get(1).text() + " " + _t.get(_t.size()-1).text());
//			} 
//		}
//  
//  
//		// "표2.메르스 확진환자 경유 병원"에 대한 처리
//  
//		System.out.println("\n표2. 메르스 확진환자 발생병원");
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
//				if(!_t.get(1).text().equals("역학조사 진행중"))
//				System.out.println(temp + " " + _t.get(1).text());
//			} 
//		}
//
//		// "표3.환자별 정보"에 대한 처리
//  
//		System.out.println("\n표3. 환자별 정보");
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
//		// "표4. 국가지정 입원치료(격리) 병상 운영 병원
// 
//		System.out.println("\n표4. 국가지정 입원치료(격리) 병상 운영 병원");
//		  
//		_itr = itr.next().select("tr").iterator();
//		_itr.next();
//		while(_itr.hasNext()){
//			Element t= _itr.next().tagName("td");
//			Elements _t = t.getAllElements();
//			  
//			System.out.println(_t.get(1).text() + " " + _t.get(2).text() + " " + _t.get(3).text() + " " + _t.get(4).text());
//			/*
//			if(_t.get(1).text().contains("번")){
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




