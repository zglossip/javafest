package com.zglossip.javafest.service;

import org.springframework.stereotype.Service;

import static com.zglossip.javafest.util.FlamesUtil.MADLINE_KAHN_AS_MRS_WHITE_IN_CLUE_SAYING_FLAMES;
import static com.zglossip.javafest.util.FlamesUtil.MADLINE_KAHN_AS_MRS_WHITE_IN_CLUE_SAYING_FLAMES_LABEL;
import static com.zglossip.javafest.util.PrintUtil.printText;

@Service
public class FlamesService {

    public void printMadelineKahnAsMrsWhiteInClueSayingFlames() {
        printText(MADLINE_KAHN_AS_MRS_WHITE_IN_CLUE_SAYING_FLAMES_LABEL);
        printText(MADLINE_KAHN_AS_MRS_WHITE_IN_CLUE_SAYING_FLAMES);
    }

}
