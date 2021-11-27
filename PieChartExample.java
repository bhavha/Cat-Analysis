import java.io.*;
import java.util.Scanner;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import javafx.scene.Group; 
import javafx.scene.*; 
import javafx.scene.image.WritableImage;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import javafx.*;
import java.io.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
// import com.itextpdf.text.pdf.DefaultFontMapper;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.*;




public class PieChartExample extends Application{
	//static int[] result = new int[8];
	static String[] result = new String[8];
	static int pass=0,fail=0,absent=0;
	static long maxMark = 1000;
		static String fname="";
        static String[] regNo = new String[9];
        static double[] marks = new double[9];
		static String[] state = new String [9];
	public static void main(String[] args) {
		
}





	 @Override
	public void start(Stage primaryStage) throws IOException{
		
		ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
		 new PieChart.Data("PASS", pass),
		 new PieChart.Data("FAIL", fail),
		 new PieChart.Data("ABSENT", absent)
		
        );
		PieChart pChart = new PieChart(pieData);
		pChart.setTitle("Program Results");
		pChart.setStartAngle(180);
		Group root = new Group(pChart);
		Scene scene = new Scene(root, 600, 400);
		primaryStage.setTitle("Pie Chart");
		primaryStage.setScene(scene);
		primaryStage.show();
		WritableImage image = scene.snapshot(null);
      File file = new File("G:\\JavaFX\\pieChart.png");
      ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);
      System.out.println("Image Saved");
	  
	  
	   Pane pane = new Pane();
		double height = 250; 
		double paneHeight = 250;
                int a=60;
                Color[] myColors = {Color.RED,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.BLUE,Color.PURPLE,Color.CYAN,Color.WHITE,Color.BLACK};
                for (int j=0; j<marks.length; j++) {
                    Rectangle rect = new Rectangle(20+(a*j), paneHeight - (height * ((marks[j])/(maxMark))), 50, height * ((marks[j])/(maxMark)));
                    rect.setFill(myColors[j]);
                    Text text1 = new Text(10+(a*j), paneHeight - (height * ((marks[j])/(maxMark))) - 40, regNo[j]+"\n,"+state[j]+":"+marks[j]);             
                    text1.setFill(Color.BLACK);
                    pane.getChildren().addAll(rect, text1);
                }
		//Create new scene, place in the stage, set title and display
		Scene scene1 = new Scene(pane, 1919, paneHeight);
                scene1.setFill(Color.BLACK);
		primaryStage.setTitle("Marks of Students Bar Graph");
		primaryStage.setScene(scene1);
		primaryStage.show();
		WritableImage image2 = scene1.snapshot(null);
      File file2 = new File("G:\\JavaFX\\tempBellChart.png");
      ImageIO.write(SwingFXUtils.fromFXImage(image2, null), "PNG", file2);
      System.out.println("Image Saved");
	   Platform.exit();
	  //PieChartExample myobj1 = new PieChartExample();
	  //myobj1.plot_pie(fname);
	  primaryStage.close();
	  //try{
		//Application.stop();
	  //}catch(Exception e){System.out.println(e);}
	  
	  
	  
	  
	  
	  
	  Platform.exit();
	}
	public static void plot_pie(String filename){
		try{
		int i=0;
            //String filename = "accounts.csv";
            try (Scanner results = new Scanner(new FileReader(filename))) {
                while (results.hasNextLine()) {
                    String line = results.nextLine();
                    while (line != null) {
                        if (i>0){
                            String[] cells = line.split(",");
                            
							result[i-1] = cells[3];
					
                            if(!results.hasNextLine()) {
                                break;
                            }
                        }
                        i++;
                        line = results.nextLine();
                        
                    }
                }
            }   
		}catch(Exception e){System.out.println(e);}	
		for(int j=0;j<8;j++)
		{
			if(result[j].equals("pass")){
				pass+=1;
			}
			else if(result[j].equals("fail")){
				fail+=1;
			}
			else if(result[j].equals("absent")){
				absent+=1;
			}
		}
		
		try{
            int i=0;
			fname+=filename;
            //String filename = "accounts.csv";
            try (Scanner results = new Scanner(new FileReader(filename))) {
                while (results.hasNextLine()) {
                    String line = results.nextLine();
                    while (line != null) {
                        if (i>0){
                            String[] cells = line.split(",");
                            regNo[i-1] = cells[0];
                            marks[i-1] = Integer.parseInt(cells[2]);
							state[i-1] = cells[1];
                            if(!results.hasNextLine()) {
                                break;
                            }
                        }
                        i++;
                        line = results.nextLine();
                        
                    }
                }
            }     
	}catch(Exception e){System.out.println(e);}
		
		
		
		
		
		
		Application.launch();
		try {
	//Create Document instance.
	Document document = new Document();
 
	//Create OutputStream instance.
	OutputStream outputStream = 
	    new FileOutputStream(new File("G:\\TestImageFile.pdf"));
 
	//Create PDFWriter instance.
        PdfWriter.getInstance(document, outputStream);
 
        //Open the document.
        document.open();
 
        //Create Image object
        Image image = Image.getInstance("G:\\JavaFX\\pieChart.png");
 		Image image1 = Image.getInstance("G:\\JavaFX\\tempBellChart.png");
 		// image1.setRotationAngle(90); 
        //Add content to the document using Image object.
        document.add(new Paragraph("PIE CHART = >"));
        document.add(image);
        document.add(new Paragraph("BAR CHART = >"));
 		document.add(image1);
        //Close document and outputStream.
        document.close();
        outputStream.close();
 
        System.out.println("Pdf created successfully.");
    } catch (Exception e) {
	e.printStackTrace();
    }
		
	}
	}
	
	