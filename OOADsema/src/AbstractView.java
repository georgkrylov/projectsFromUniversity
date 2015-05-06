import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;


public class AbstractView implements ActionListener{

	static JLabel buffer;
	AbstractRunTime rt;
	gui gui;
	public AbstractView(AbstractRunTime rt,gui gui) {
		this.rt = rt;
		this.gui=gui;
		rt.rtv=this;
		prepare();
	}

	public void prepare(){
		int readerscount = -1;
		int writerscount = -1;
		gui.jTextArea1.setText("");
		gui.jTextArea2.setText("");
		synchronized(rt.agents){
			Iterator it =  rt.agents.iterator();
			while (it.hasNext()){
				Agent temp = (Agent) it.next();
				if (temp.getClass()== FairReader.class) {
					readerscount++;
					gui.jTextArea1.setText(gui.jTextArea1.getText()+"FairReader "+ Integer.toString(readerscount)+"\n");
				}
				if (temp.getClass()== Reader.class) {
					readerscount++;
					gui.jTextArea1.setText(gui.jTextArea1.getText()+"Reader "+ Integer.toString(readerscount)+"\n");
				}
				if (temp.getClass()== FairWriter.class) {
					writerscount++;
					gui.jTextArea2.setText(gui.jTextArea2.getText()+"FairWriter "+ Integer.toString(writerscount)+"\n");
				}
				if (temp.getClass()== Writer.class) {
					writerscount++;
					gui.jTextArea2.setText(gui.jTextArea2.getText()+"Writer "+ Integer.toString(writerscount)+"\n");
				}
				if (temp.getClass()== Buffer.class) {
					buffer = gui.jLabel3;
					Buffer bufferr = (Buffer)temp;
					buffer.setText(bufferr.read(rt.time));


				}
			}
		}
	}
	public void repaint(){
		gui.jLabel9.setText(Integer.toString(550-rt.speed));
		int readerHighlightStart=0;
		int readerHighlightEnd=0;
		int writerHighlightStart=0;
		int writerHiglightEnd=0;
		//gui.repaint();
		synchronized(rt.agents){
			Iterator it =  rt.agents.iterator();
			int readerscount = -1;
			int writerscount = -1;
			
			gui.jTextArea1.setText("");
			gui.jTextArea2.setText("");
			gui.jLabel13.setText(Integer.toString(rt.getTime()));
			while (it.hasNext()){
				Agent temp = (Agent) it.next();
				if (temp.getClass()== FairReader.class) {
					readerscount++;
					FairReader readerr = (FairReader)temp;
					
					int sizeofArea = gui.jTextArea1.getText().length();
					String tempString ="FairReader "+ Integer.toString(readerscount) +"\n"+readerr.readerString+"\n";
					gui.jTextArea1.setText(gui.jTextArea1.getText()+tempString);
					if (readerr.active) {
						readerHighlightStart = sizeofArea;
						readerHighlightEnd = sizeofArea+tempString.length();
					}
				}
				if (temp.getClass()== Reader.class) {
					readerscount++;
					Reader readerr = (Reader)temp;
					
					int sizeofArea = gui.jTextArea1.getText().length();
					String tempString ="Reader "+ Integer.toString(readerscount) +"\n"+readerr.readerString+"\n";
					gui.jTextArea1.setText(gui.jTextArea1.getText()+tempString);
					if (readerr.active) {
						readerHighlightStart = sizeofArea;
						readerHighlightEnd = sizeofArea+tempString.length();
					}
				}
				if (temp.getClass()== FairWriter.class) {
					writerscount++;
					FairWriter writerr = (FairWriter)temp;
					
					int sizeofArea = gui.jTextArea2.getText().length();
					String tempString = "FairWriter "+ Integer.toString(writerscount)+"\n"+writerr.jobLeft()+"\n";
					gui.jTextArea2.setText(gui.jTextArea2.getText()+tempString);
					if (writerr.active) {
						writerHighlightStart=sizeofArea;
						writerHiglightEnd=sizeofArea+tempString.length();
					}
				}
				if (temp.getClass()== Writer.class) {
					writerscount++;
					 Writer writerr = ( Writer)temp;
					
					int sizeofArea = gui.jTextArea2.getText().length();
					String tempString = "Writer "+ Integer.toString(writerscount)+"\n"+writerr.jobLeft()+"\n";
					gui.jTextArea2.setText(gui.jTextArea2.getText()+tempString);
					if (writerr.active) {
						writerHighlightStart=sizeofArea;
						writerHiglightEnd=sizeofArea+tempString.length();
					}
				}
				if (temp.getClass()== Buffer.class) {
					buffer = gui.jLabel3;
					Buffer bufferr = (Buffer)temp;
					buffer.setText(bufferr.read(rt.time));


				}
			}

			if (readerHighlightEnd-readerHighlightStart != 0){
				Highlighter h =  gui.jTextArea1.getHighlighter();
				h.removeAllHighlights();
				try {
					h.addHighlight(readerHighlightStart ,
							readerHighlightEnd,
							DefaultHighlighter.DefaultPainter);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					//		e.printStackTrace();
				}

			}
			if (writerHiglightEnd-writerHighlightStart != 0){
				Highlighter h =  gui.jTextArea2.getHighlighter();
				h.removeAllHighlights();
				try {
					h.addHighlight(writerHighlightStart ,
							writerHiglightEnd,
							DefaultHighlighter.DefaultPainter);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					//		e.printStackTrace();
				}

			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand()=="Step"){
			if (rt.runBoolean==true) {
				rt.runBoolean=false;
				gui.jButton5.setText("Play");
			} 
			StepClass worker = new StepClass(rt);
			worker.execute();
		}
		if (e.getActionCommand()=="Apply"){
			if ((gui.jTextField1.getText().matches(".*\\d+.*"))){
				int i = 1;
				i = Integer.parseInt(gui.jTextField1.getText());
				//	System.out.println(i);
				rt.chpw = i;
			}
		}
		if (e.getActionCommand()=="Speed up"){if (rt.speed>50){rt.speed-=50;}};
		if (e.getActionCommand()=="Speed down"){rt.speed+=50; };
		if (e.getActionCommand() == "Play" || e.getActionCommand() == "Pause" ){
			if (rt.runBoolean==true) {
				rt.runBoolean=false;

				gui.jButton5.setText("Play");

			} else {
				rt.runBoolean=true;
				gui.jButton5.setText("Pause");
				PausePlayClass worker = new PausePlayClass(rt);
				worker.execute();

			}
		}

	}
}

