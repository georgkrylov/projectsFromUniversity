import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;



public class RunTimeView {
	static ArrayList<JLabel> readers;
	static ArrayList<JLabel> writers;
	static JLabel buffer;
	RunTime rt;

	public RunTimeView(RunTime rt) {
		readers = new ArrayList();
		writers = new ArrayList();
		this.rt = rt;
		Iterator it =  rt.agents.iterator();
		int readerscount = -1;
		int writerscount = -1;
		while (it.hasNext()){
			Agent temp = (Agent) it.next();
			if (temp.getClass()== Reader.class) {
				readerscount++;
				rt.gui.jTextArea1.setText(rt.gui.jTextArea1.getText()+"Reader "+ Integer.toString(readerscount)+"\n");
			}
			if (temp.getClass()== Writer.class) {
				writerscount++;
				rt.gui.jTextArea2.setText(rt.gui.jTextArea2.getText()+"Writer "+ Integer.toString(writerscount)+"\n");
			}
			if (temp.getClass()== Buffer.class) {
				buffer = rt.gui.jLabel3;
				Buffer bufferr = (Buffer)temp;
				buffer.setText(bufferr.read());


			}
		}
	}
	public void repaint(){
		//	while (1==1){
		rt.gui.jLabel9.setText(Integer.toString(rt.speed));
		int readerHighlightStart=0;
		int readerHighlightEnd=0;
		int writerHighlightStart=0;
		int writerHiglightEnd=0;
		synchronized(rt.agents){
			Iterator it =  rt.agents.iterator();
			int readerscount = -1;
			int writerscount = -1;
			
			rt.gui.jTextArea1.setText("");
			rt.gui.jTextArea2.setText("");
			rt.gui.jLabel13.setText(Integer.toString(rt.getTime()));
			while (it.hasNext()){
				Agent temp = (Agent) it.next();
				if (temp.getClass()== Reader.class) {
					readerscount++;
					Reader readerr = (Reader)temp;

					int sizeofArea = rt.gui.jTextArea1.getText().length();
					String tempString ="Reader "+ Integer.toString(readerscount) +"\n"+readerr.readerString+"\n";
					//	System.out.println(sizeofArea);
					rt.gui.jTextArea1.setText(rt.gui.jTextArea1.getText()+tempString);
					if (readerr.active) {
						readerHighlightStart = sizeofArea;
						readerHighlightEnd = sizeofArea+tempString.length();
					}
				}
				if (temp.getClass()== Writer.class) {
					writerscount++;
					Writer writerr = (Writer)temp;
					int sizeofArea = rt.gui.jTextArea2.getText().length();
					String tempString = "Writer "+ Integer.toString(writerscount)+"\n"+writerr.jobLeft()+"\n";
					rt.gui.jTextArea2.setText(rt.gui.jTextArea2.getText()+tempString);
					if (writerr.active) {
						writerHighlightStart=sizeofArea;
						writerHiglightEnd=sizeofArea+tempString.length();
						}
					}
				if (temp.getClass()== Buffer.class) {
					buffer = rt.gui.jLabel3;
					Buffer bufferr = (Buffer)temp;
					buffer.setText(bufferr.read());


				}
			}
		}
		if (readerHighlightEnd-readerHighlightStart != 0){
			Highlighter h =  rt.gui.jTextArea1.getHighlighter();
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
			Highlighter h =  rt.gui.jTextArea2.getHighlighter();
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
	//}
}
