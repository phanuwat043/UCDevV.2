package com.ucdev.draw.api;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

import java.io.File;
import java.io.IOException;
import opennlp.tools.util.Sequence;

public class OpenNlpTest {

    public static void main(String[] args) throws IOException {
        POSModel model = new POSModelLoader().load(new File("en-pos-maxent.bin"));
        POSTaggerME tagger = new POSTaggerME(model);

        //String input = "Can anyone help me dig through OpenNLP's horrible documentation?";
        String sent[] = new String[]{"user", "can", "login", "to", "the", "system"};
        String a = "user can login to the system";
        String tags[];
        tags = tagger.tag(sent);
        
        //double probs[] = tagger.probs();
        //Sequence topSequences[] = tagger.topKSequences(sent);
        for (int i = 0; i < tags.length; i++) {
            System.out.println("tags : "+tags[i]);
        }
    }
}
