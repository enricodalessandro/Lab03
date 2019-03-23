package it.polito.tdp.spellchecker.controller;


import java.awt.Label;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.spellchecker.*;
import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class SpellCheckerController {
	Dictionary dizionario = new Dictionary();
	ObservableList<String> languages = FXCollections.observableArrayList("Italiano","English");
    @FXML
    private ChoiceBox<String> box_choice;

    @FXML
    private TextArea text_area;

    @FXML
    private Button btn_check;
    
    @FXML
    private Text txt_tesec;
    @FXML
    private Text txt_nerrori;
    @FXML
    private TextArea text_result;
 
    
    @FXML
    private Button btn_clear;
    @FXML
    public void initialize() {
    	box_choice.setValue("English");
    	box_choice.setItems(languages);
    }

    @FXML
    void doCheckSpell(ActionEvent event) {
    	int cont=0;
    	String lingua= box_choice.getValue();
    		dizionario.loadDictionary(lingua);
    		List<String> b=this.prendiTesto();
    		List <RichWord> a=dizionario.SpellList(b);
    		for (RichWord rw: a)
    		{
    			if(rw.isCorretta()==false)
    			{
    				text_result.appendText(rw.getWord()+"\n");
        			cont++;

    			}
    		}
    	text_area.clear();
    	
    	txt_nerrori.setText("Il numero di parole non corrette e': "+cont);
    	txt_tesec.setText("Il tempo di esecuzione è stato di: "+System.nanoTime());

    	}
    
  
    @FXML
    void doClear(ActionEvent event) {
    	text_result.clear();
    	text_area.clear();
    	txt_tesec.setText(" ");
    	txt_nerrori.setText(" ");

    }
    
   
    private List<String> prendiTesto (){
    	LinkedList<String> parole=new LinkedList<String>();
    	String totale=text_area.getText();
    	String [] array=totale.split(" ");
    	int lung=array.length;
    	for (int i=0;i<lung;i++)
    		if(array[i]!=null)
    	{
    		array[i].toLowerCase();
    		array[i].replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]\n", "");
    		array[i].trim();
    		parole.add(array[i]);
    	}
    	
    	
		return parole;
    	
    }

}
