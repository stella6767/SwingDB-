package address.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import address.dao.UserDao;
import address.model.User;

public class MainFrame extends JFrame {

	MainFrame mainFrame = this;
	Container backgroundPanel;
	JPanel topPanel, listPanel, menuPanel;
	JButton addButton, homeButton, frButton, coButton, scButton, faButton;
	JButton deleteButton, updateButton; //추가

	JList<User> userList;
	DefaultListModel<User> listModel;

	public MainFrame() {
		setTitle("주소록 메인");
		setSize(400, 500);

		// ȭ�� ���߾ӿ� ��ġ�ϴ� �Լ�
		setLocationRelativeTo(null);

		// �����ӿ� X��ư�� Ŭ���� �� ���α׷��� �������ִ� �Լ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 모든 디자인 초기화(메모리에 올린다) 함수
		initObject();

		// 데이터 초기화 (DB에 데이터 가져오기)
		initData();

		// 화면 배치하기 + 디자인하기 함수
		initDesign();

		// 리스너 등록 함수
		initListener();

		// 그림을 그려주는 함수
		setVisible(true);
	}

	// listModel에 데이터를 채워넣으면 됨
	void initData() {
		listModel.clear();
		ArrayList<User> list = UserDao.찾기();

		for (int i = 0; i < list.size(); i++) {
			listModel.addElement(list.get(i));
		}
	}

	void searchData(String relation) {
		listModel.clear();
		ArrayList<User> list = UserDao.검색(relation);

		for (int i = 0; i < list.size(); i++) {
			listModel.addElement(list.get(i));
		}
	}

	//  리스너 등록 함수
	void initListener() {
		
		homeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				initData();
			}
		});

		frButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchData("친구");
			}
		});

		coButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchData("회사");
			}
		});

		scButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchData("학교");
			}
		});

		faButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchData("가족");
			}
		});

		// 테스트
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddFrame(mainFrame);
				mainFrame.setVisible(false);
			}
		});

		userList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String value = userList.getSelectedValue().toString();
				System.out.println(value);
				//  split 함수는 문자열을 원하는 특정 문자로 쪼갤 때 사용 (리턴: String[])
				String ids = value.split("\\.")[0];
				int id = Integer.parseInt(ids); // 문자열을 int로 변경하는 방법

				new DetailFrame(mainFrame, id);
				mainFrame.setVisible(false);
			}
		});
	}

	// 모든 컴퍼넌트 new 해줄 것임(메모리에 초기화 시킴)
	void initObject() {
		backgroundPanel = getContentPane();
		backgroundPanel.setLayout(new BorderLayout());

		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2, 1)); // ��, ��
		menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(1, 4));

		listPanel = new JPanel();
		listPanel.setLayout(new BorderLayout());

		addButton = new JButton("추가");
		homeButton = new JButton("주소록 전체");
		frButton = new JButton("친구");
		coButton = new JButton("회사");
		scButton = new JButton("학교");
		faButton = new JButton("가족");

		listModel = new DefaultListModel<User>();
		userList = new JList<User>(listModel);
	}

	// 화면에 배치 + 디자인
	void initDesign() {
		topPanel.add(homeButton);
		menuPanel.add(frButton);
		menuPanel.add(coButton);
		menuPanel.add(scButton);
		menuPanel.add(faButton);

		userList.setFixedCellHeight(50);

		topPanel.add(menuPanel);
		listPanel.add(new JScrollPane(userList));

		backgroundPanel.add(topPanel, BorderLayout.NORTH);
		backgroundPanel.add(listPanel, BorderLayout.CENTER);
		backgroundPanel.add(addButton, BorderLayout.SOUTH);
	}

}
