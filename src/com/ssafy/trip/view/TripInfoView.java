package com.ssafy.trip.view;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.ssafy.trip.model.dto.FestivalDto;
import com.ssafy.trip.model.dto.TripDto;
import com.ssafy.trip.model.dto.TripSearchDto;
import com.ssafy.trip.model.service.TripService;
import com.ssafy.trip.model.service.TripServiceImpl;

public class TripInfoView {

	/** model들 */
	private TripService tripService;

	/** main 화면 */
	private JFrame frame;

	/** 관광지 이미지 표시 Panel */
	private JLabel imgL;
	private JLabel[] tripInfoL;

	/** 조회 조건 */
	private JComboBox<String> findC;
	private JTextField wordTf;
	private JButton searchBt;
	
	/** 관련 지역 축제 버튼 */
	private JButton festivalBt;

	/** 조회 내용 표시할 table */
	private DefaultTableModel tripModel;
	private JTable tripTable;
	private JScrollPane tripPan;
	private String[] title = { "번호", "관광지명", "도로명주소", "지번주소", "전화번호" };

	/** 검색 조건 */
	private String key;
	private String[] choice = { "검색조건선택", "관광지명", "주소" };
	/** 검색할 단어 */
	private String word;

	/** 화면에 표시하고 있는 주택 */
	private TripDto curTrip;

	public TripInfoView() {
		/* Service들 생성 */
		tripService = new TripServiceImpl();

		/* 메인 화면 설정 */
		frame = new JFrame("Enjoy! Trip - 즐거운 여행");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				frame.dispose();
			}
		});

		setMain();

		frame.setSize(1200, 800);
		frame.setResizable(true);
		frame.setVisible(true);
		showTripInfo(0);
	}

	private void showTripInfo(int num) {
		curTrip = tripService.search(num);

		tripInfoL[0].setText("");
		tripInfoL[1].setText("");
		tripInfoL[2].setText(curTrip.getTouristDestination());
		tripInfoL[3].setText(curTrip.getStreetAddress());
		tripInfoL[4].setText(curTrip.getLotAddress());
		tripInfoL[5].setText(curTrip.getLat() + "");
		tripInfoL[6].setText(curTrip.getLng() + "");
		tripInfoL[7].setText(curTrip.getTel());
		tripInfoL[8].setText(curTrip.getInfo());
		tripInfoL[9].setText("");

		ImageIcon icon = null;
		if (curTrip.getImg() != null && curTrip.getImg().trim().length() != 0) {
			String img = curTrip.getImg();
			File file = new File("img", img);

			if (!file.exists())
				img = "no_image.jpg";
			icon = new ImageIcon("img/" + img);

		} else {
			icon = new ImageIcon("img/no_image.jpg");
		}
		Image image = icon.getImage();
		Image changeImage = image.getScaledInstance(570, 470, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImage);
		imgL.setIcon(changeIcon);
	}

	/** 메인 화면인 관광지 목록을 위한 화면 셋팅하는 메서드 */
	public void setMain() {

		/* 왼쪽 화면을 위한 설정 */
		JPanel left = new JPanel(new BorderLayout());
		JPanel leftCenter = new JPanel(new BorderLayout(0, 10));
		JPanel leftR = new JPanel(new GridLayout(10, 2));
		leftR.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

		String[] info = { "", "", "관광지명", "도로명주소", "지번주소", "위도", "경도", "전화번호", "관광지정보", "" };
		int size = info.length;
		JLabel infoL[] = new JLabel[size];
		tripInfoL = new JLabel[size];
		for (int i = 0; i < size; i++) {
			infoL[i] = new JLabel(info[i]);
			tripInfoL[i] = new JLabel("");
			leftR.add(infoL[i]);
			leftR.add(tripInfoL[i]);
		}
		imgL = new JLabel();

		/* 관련 지역 축제 버튼 생성 */
		festivalBt = new JButton("관련 지역 축제");

		/*
		 * 이미지와 축제 버튼을 묶는 패널
		 */
		JPanel imagePanel = new JPanel(new BorderLayout(0, 10));

		/* 가운데에는 관광지 이미지 */
		imagePanel.add(imgL, BorderLayout.CENTER);

		/* 이미지 아래에는 축제 버튼 */
		imagePanel.add(festivalBt, BorderLayout.SOUTH);

		/*
		 * 왼쪽 가운데 영역 구성
		 *
		 * CENTER : 이미지 + 축제 버튼
		 * SOUTH  : 관광지 상세 정보
		 */
		leftCenter.add(imagePanel, BorderLayout.CENTER);
		leftCenter.add(leftR, BorderLayout.SOUTH);

		left.add(new JLabel("관광지 정보", JLabel.CENTER), "North");
		left.add(leftCenter, "Center");

		/* 오른쪽 화면을 위한 설정 */
		JPanel right = new JPanel(new BorderLayout());
		JPanel rightTop = new JPanel(new GridLayout(4, 2));

		JPanel rightTop2 = new JPanel(new GridLayout(1, 3));
		String[] item = { "검색조건선택", "관광지명", "주소" };
		findC = new JComboBox<String>(item);
		wordTf = new JTextField();
		searchBt = new JButton("검색");

		rightTop2.add(findC);
		rightTop2.add(wordTf);
		rightTop2.add(searchBt);

		rightTop.add(new Label(""));
		rightTop.add(new Label(""));
		rightTop.add(rightTop2);
		rightTop.add(new Label(""));

		JPanel rightCenter = new JPanel(new BorderLayout());
		tripModel = new DefaultTableModel(title, 20);
		tripTable = new JTable(tripModel);
		tripPan = new JScrollPane(tripTable);
		tripTable.setColumnSelectionAllowed(true);
		rightCenter.add(new JLabel("광광지 정보", JLabel.CENTER), "North");
		rightCenter.add(tripPan, "Center");

		right.add(rightTop, "North");
		right.add(rightCenter, "Center");

		JPanel mainP = new JPanel(new GridLayout(1, 2));

		mainP.add(left);
		mainP.add(right);

		mainP.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		frame.add(mainP, "Center");

		tripTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = tripTable.getSelectedRow();
				int code = Integer.parseInt(((String) tripModel.getValueAt(row, 0)).trim());
				showTripInfo(code);
			}
		});

		// complete code #01
		// 아래의 코드를 참조하여 아래 라인을 uncomment 하고 searchBt.addActionList() 를 Lambda 표현식으로 바꾸세요.
		// searchBt.addActionListener( /* 여기 */ );

		// 참조코드 시작 - 위 코드를 완성 후 삭제 또는 comment 처리하세요.
		
		searchBt.addActionListener(e -> searchTrips());
		festivalBt.addActionListener(e -> showRelatedFestivals());
//		ActionListener buttonHandler = new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				searchTrips();
//			}
//		};
		
//		searchBt.addActionListener( buttonHandler );
		// 참조코드 종료

		showTrips();
	}
	
	private void showRelatedFestivals() {

	    /*
	     * 현재 선택된 관광지가 없는 경우
	     */
	    if (curTrip == null) {
	        JOptionPane.showMessageDialog(
	                frame,
	                "관광지를 먼저 선택해주세요."
	        );
	        return;
	    }

	    /*
	     * 현재 선택된 관광지의 도로명주소
	     */
	    String streetAddress = curTrip.getStreetAddress();

	    /*
	     * 도로명주소가 null이거나 빈 문자열인 경우
	     */
	    if (streetAddress == null || streetAddress.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(
	                frame,
	                "선택한 관광지의 도로명주소가 없습니다."
	        );
	        return;
	    }

	    /*
	     * 앞뒤 공백 제거
	     */
	    streetAddress = streetAddress.trim();

	    /*
	     * 앞에서 두 글자를 자를 수 없는 경우
	     */
	    if (streetAddress.length() < 2) {
	        JOptionPane.showMessageDialog(
	                frame,
	                "도로명주소에서 지역명을 추출할 수 없습니다."
	        );
	        return;
	    }

	    try {
	        /*
	         * 도로명주소의 앞 두 글자 추출
	         *
	         * "서울특별시 중구 ..." → "서울"
	         * "부산광역시 해운대구 ..." → "부산"
	         */
	        String cityName = streetAddress.substring(0, 2);

	        /*
	         * 추출한 지역명으로 축제 목록 검색
	         */
	        List<FestivalDto> festivals =
	                tripService.serchAllFestival(cityName);

	        /*
	         * 검색 결과를 모달 창으로 출력
	         */
	        showFestivalDialog(cityName, festivals);

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(
	                frame,
	                "축제 정보를 불러오는 중 오류가 발생했습니다.\n"
	                        + e.getMessage()
	        );
	    }
	}
	
	/**
	 * 검색된 축제 목록을 모달 창으로 출력한다.
	 */
	private void showFestivalDialog(
	        String sido,
	        List<FestivalDto> festivals
	) {

	    /*
	     * 검색 결과가 없는 경우
	     */
	    if (festivals == null || festivals.isEmpty()) {
	        JOptionPane.showMessageDialog(
	                frame,
	                sido + " 지역의 축제 정보가 없습니다."
	        );
	        return;
	    }

	    /*
	     * 축제 테이블 열 이름
	     */
	    String[] festivalTitle = {
	            "연번",
	            "축제명",
	            "시도",
	            "시작일",
	            "종료일"
	    };

	    /*
	     * 축제 JTable의 데이터 모델
	     */
	    DefaultTableModel festivalModel =
	            new DefaultTableModel(festivalTitle, 0) {

	                @Override
	                public boolean isCellEditable(
	                        int row,
	                        int column
	                ) {
	                    /*
	                     * 셀 직접 수정 금지
	                     */
	                    return false;
	                }
	            };

	    /*
	     * FestivalDto 목록을 JTable 행으로 변환
	     */
	    for (FestivalDto festival : festivals) {

	        Object[] rowData = {
	                festival.getNum(),
	                festival.getFestivalName(),
	                festival.getCity(),
	                festival.getStartDate(),
	                festival.getEndDate()
	        };

	        festivalModel.addRow(rowData);
	    }

	    JTable festivalTable =
	            new JTable(festivalModel);

	    festivalTable.setRowHeight(25);

	    /*
	     * 열 제목을 누르면 정렬 가능
	     */
	    festivalTable.setAutoCreateRowSorter(true);

	    JScrollPane festivalScrollPane =
	            new JScrollPane(festivalTable);

	    /*
	     * 세 번째 인자 true:
	     * 모달 창으로 설정
	     */
	    JDialog festivalDialog =
	            new JDialog(
	                    frame,
	                    sido + " 관련 지역 축제",
	                    true
	            );

	    festivalDialog.setLayout(
	            new BorderLayout(10, 10)
	    );

	    JLabel titleLabel =
	            new JLabel(
	                    sido + " 관련 축제 목록",
	                    JLabel.CENTER
	            );

	    festivalDialog.add(
	            titleLabel,
	            BorderLayout.NORTH
	    );

	    festivalDialog.add(
	            festivalScrollPane,
	            BorderLayout.CENTER
	    );

	    festivalDialog.setSize(850, 450);

	    /*
	     * 기존 관광지 창 중앙에 표시
	     */
	    festivalDialog.setLocationRelativeTo(frame);

	    /*
	     * 모달 창 표시
	     */
	    festivalDialog.setVisible(true);
	}

	/** 검색 조건에 맞는 관광지 검색 */
	private void searchTrips() {
		word = wordTf.getText().trim();
		key = choice[findC.getSelectedIndex()];
		showTrips();
	}

	/**
	 * 관광지 목록을 갱신하기 위한 메서드
	 */
	public void showTrips() {
		TripSearchDto tripSearchDto = new TripSearchDto();
		if (key != null) {
			if (key.equals("관광지명")) {
				tripSearchDto.setTouristDestination(word);
			} else if (key.equals("주소")) {
				tripSearchDto.setSido(word);
			}
		}

		if (word == null || word.trim().length() == 0)
			findC.setSelectedIndex(0);

		List<TripDto> trips = tripService.searchAll(tripSearchDto);
		if (trips != null) {
			int i = 0;
			String[][] data = new String[trips.size()][5];
			for (TripDto trip : trips) {
				data[i][0] = "" + trip.getNum();
				data[i][1] = trip.getTouristDestination();
				data[i][2] = trip.getStreetAddress();
				data[i][3] = trip.getLotAddress();
				data[i++][4] = trip.getTel();
			}
			tripModel.setDataVector(data, title);
		}
	}

	public static void main(String[] args) {
		new TripInfoView();
	}
}
