package address.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import address.dao.UserDao;
import address.model.User;

public class DetailFrame extends JFrame{

	MainFrame mainFrame;
	DetailFrame detailFrame = this; //컨텍스트 
	int userId;
	
	JLabel nameLabel, phoneLabel, addressLabel, groupLabel;
	JTextField nameTF, phoneTF, addressTF, groupTF;  
	JButton updateButton, deleteButton;
	
	Container backgroundPanel; // ���� �ִ� JFrame�� ������ �ִ� �г�
	
	
	public DetailFrame(MainFrame mainFrame, int id) {
		this.mainFrame = mainFrame;
		this.userId = id;
		setTitle("주소록 상세보기");
		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		initObject();
		
		initData();
		
		initDesign();
		
		initListener();
		
		setVisible(true);
	}
	
	void initData() {
		User user = UserDao.찾기(userId);
		nameTF.setText(user.getName());
		phoneTF.setText(user.getPhone());
		addressTF.setText(user.getAddress());
		groupTF.setText(user.getRelation());
	}
	
	void initDesign() {
		backgroundPanel.setLayout(new GridLayout(5,2));	
		
		backgroundPanel.add(nameLabel);
		backgroundPanel.add(nameTF);
		backgroundPanel.add(phoneLabel);
		backgroundPanel.add(phoneTF);
		backgroundPanel.add(addressLabel);
		backgroundPanel.add(addressTF);
		backgroundPanel.add(groupLabel);
		backgroundPanel.add(groupTF);
		
		backgroundPanel.add(updateButton);
		backgroundPanel.add(deleteButton);
		
	}
	
	void initObject() {
		backgroundPanel = getContentPane();
		
		nameLabel = new JLabel("이름");
		phoneLabel = new JLabel("전화번호");
		addressLabel = new JLabel("주소");
		groupLabel = new JLabel("그룹");
		
		nameTF = new JTextField();
		phoneTF = new JTextField();
		addressTF = new JTextField();
		groupTF = new JTextField();
		
		updateButton = new JButton("수정하기");
		deleteButton = new JButton("삭제하기");
	}
	
	void initListener() {
		
		// 리스너
		deleteButton.addActionListener(new ActionListener() {
			
			// �콜백함수
			@Override
			public void actionPerformed(ActionEvent e) {
				// id값을 찾아야 됨
				int id = userId;
				// UserDao의 삭제함수를 호출
				UserDao.삭제하기(id);
				// detailFrame 을 닫고
				detailFrame.dispose();
				// 데이터 갱신
				mainFrame.initData();
				// mainFrame을 보이게 하기
				mainFrame.setVisible(true);
			}
		});
		
		
		updateButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = userId;
				String name= nameTF.getText();
				String phone = phoneTF.getText();
				String address = addressTF.getText();
				String relation = groupTF.getText();
				
				UserDao.수정하기(id, name, phone, address, relation);
				detailFrame.dispose();
				mainFrame.initData();
				mainFrame.setVisible(true);
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mainFrame.setVisible(true);
			}
		});
	}
}



