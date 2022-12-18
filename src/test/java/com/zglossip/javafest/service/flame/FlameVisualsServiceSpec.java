package com.zglossip.javafest.service.flame;

import com.zglossip.javafest.base.TestBase;
import com.zglossip.javafest.domain.AsciiImage;
import com.zglossip.javafest.domain.TriFunction;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class FlameVisualsServiceSpec extends TestBase {

  FlameVisualsService flameVisualsService = new FlameVisualsService();

  @Test
  public void testPrintDefaultPicture() {
    //Given test data
    final Integer width = null;
    final Integer height = null;

    //When
    final AsciiImage result = flameVisualsService.getMkAscii(width, height);

    //Then
    assert result.getImage().equals(getDefaultMkAscii());
    assert result.getWidth() == FlameVisualsService.DEFAULT_SIZE;
  }

  @Test
  public void testPrintDefaultInvertedPicture() {
    //Given test data
    final Integer width = null;
    final Integer height = null;

    //When
    final AsciiImage result = flameVisualsService.getInvertedMkAscii(width, height);

    //Then
    assert result.getImage().equals(getInvertedDefaultMkAscii());
    assert result.getWidth() == FlameVisualsService.DEFAULT_SIZE;
  }

  @Test
  public void testPrintCustomPicture() {
    //Given test data
    final Integer width = null;
    final Integer height = null;

    //When
    final AsciiImage result = flameVisualsService.getCustomAscii("./src/test/resources/good_for_her.jpg", width, height);

    //Then
    assert result.getImage().equals(getDefaultAltAscii());
    assert result.getWidth() == FlameVisualsService.DEFAULT_SIZE;
  }

  @Test
  public void testPrintInvertedCustomPicture() {
    //Given test data
    final Integer width = null;
    final Integer height = null;

    //When
    final AsciiImage result = flameVisualsService.getCustomAsciiInverted("./src/test/resources/good_for_her.jpg", width, height);

    //Then
    assert result.getImage().equals(getDefaultInvertedAltAscii());
    assert result.getWidth() == FlameVisualsService.DEFAULT_SIZE;
  }

  @Test
  public void testPrintPictureWithFunction() {
    //Given test data
    final Integer width = null;
    final Integer height = null;

    final TriFunction<BufferedImage, Integer, Integer, Color> colorFunc = (i, x, y) -> Color.CYAN;

    //When
    final AsciiImage result = flameVisualsService.getAsciiStringFromImage(width, height, getTestImage(), colorFunc);

    //Then
    assert result.getImage().equals(getAllCyanImage());
    assert result.getWidth() == flameVisualsService.DEFAULT_SIZE;
  }

  @Test
  public void testPrintPicture() {
    //Given test data
    final Integer width = null;
    final Integer height = null;

    //When
    final AsciiImage result = flameVisualsService.getAsciiStringFromImage(width, height, getTestImage(), null);

    //Then
    assert result.getImage().equals(getDefaultMkAscii());
    assert result.getWidth() == flameVisualsService.DEFAULT_SIZE;
  }

  @Test
  public void testPrintPictureWithWidth() {
    //Given test data
    final Integer width = 100;
    final Integer height = null;

    //When
    final AsciiImage result = flameVisualsService.getAsciiStringFromImage(width, height, getTestImage(), null);

    //Then
    assert result.getImage().equals(get100MkAscii());
    assert result.getWidth() == width;
  }

  @Test
  public void testPrintPictureWithHeight() {
    //Given test data
    final Integer width = null;
    final Integer height = 100;

    //When
    final AsciiImage result = flameVisualsService.getAsciiStringFromImage(width, height, getTestImage(), null);

    //Then
    assert result.getImage().equals(get100MkAscii());
    assert result.getWidth() == 100;
  }

  @Test
  public void testPrintPictureWithHeightAndWidth() {
    //Given test data
    final Integer width = 150;
    final Integer height = 100;

    //When
    final AsciiImage result = flameVisualsService.getAsciiStringFromImage(width, height, getTestImage(), null);

    //Then
    assert result.getImage().equals(get100Height150WidthMkAscii());
    assert result.getWidth() == width;

  }

  @Test
  public void testGetSmallerThanWidthFooter() {
    //Given
    final Integer width = 10;

    //When
    final String result = flameVisualsService.getFooter(width);

    //Then
    assert result.equals(getNoWidthFooter());
  }

  @Test
  public void testGetFooterWithWidth() {
    //Given
    final Integer width = 500;

    //When
    final String result = flameVisualsService.getFooter(width);

    //Then
    assert result.equals(get500Footer());
  }

  private static BufferedImage getTestImage() {
    try {
      final InputStream inputStream = new ClassPathResource("madeline_kahn_as_mrs_white_in_clue_saying_flames.png").getInputStream();
      return ImageIO.read(inputStream);
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static String getDefaultAltAscii() {
    return "(JI3uf3futZxeYyayY]axyxa22xCJ7)Lv{C}Ci))(T7vLzsvccc*//?zzzzsT!_=//JTz//*r!!++=,,^cz/z?LLTJ|CqqqqS]YnZIo}|7}o51C(LT7i(1o[}FF7{}7C[CJC5elt31f}}fIiFfl5utI3|}{|ICFiFf1C{u%&&%%%QQQNWWQQWWWWNNNNNMNMM0M0g0$g\n" +
            "|)f1uC3I1toxZxxxjy]]ZYYjxxaIvJ7TvCC}f||zLTTTLz*L*!cc*zzz/?*zvc^+;,<c/c**r++____<c>r*zz?ss))J(Co2wSYe5Ce{((}oYt{|vTJ{|1enC(((i}vIuFvIZnutIIC{}I}iCIn5ulfI(}{iICiiF31{{W&%%%&%%QWWWWQQQQWWNNNNNMNM0MM000g0\n" +
            "|JIt[}C1[eYxyxZx]222ayyYjxat7JvTJCCf{F)J*/L*czc?c!c**/zz/cczLc>;++<<=_:::'::^=;>+!cc///?sLTv)v7||yne5ieF(7}oYt{(LL){FuonnF{{}{TtlF)lZnutICCi3f{i}te5ulf3(}{FI{Fii13{}W&&&%%QQQWWWWQQWWWNNNNNMNMMMM00000g\n" +
            "{JIt[fC13e5Y5eeZx22]2YyYj]]3)7vTTC}C{(vvsrc?<!*/!!c//??/c**Ts/+=<!<<>+>;^^,^===>+++!c*zz?sssssLTT)|eeio7(7Ce51{|1u1{(It1fI3neI3ff}Fue[t3}C{{If}i}tZ5uufI(fiFfCiFit}{N%%%%%%QQWNWWWQQWWWNNNNNNMMM0MM0Mggg\n" +
            "{JIlntCl1[nZYo5Zoayyxy]x]2jtv7vLT}IIF(JvL*+c!+z/!c*zsL/*c/?Ls/c++++++>>;>;;;>>+!!rccc*//*/*/sLLs?sL7[CoJ7JCe51I}iTsc*/+!L??itIF3(7|C}}3333C}II{ifl5ZulC3(f{iI{iFilCCQ%%%%%QQWNNWWWWWWWWNMNNNNNM0000g0$0B\n" +
            "i)Iun}Cf1u5xx55ZY]]2axZYYy][J)vLJi*!;=^=c/r>!;zc!c/zL?//*?zs?/c+<+c*cc!!+<+!+!ccrccrc**cc*zsvL/;!ssL)}eJ7)Ce1iJz=,>>/zccc*Lr/ssLLJ(ii{C{{}C}fI}if[xZuu}I(}{iI{ii{t}D%Q%%QQQWN0WNNWWWWWNNNMMNM00000000BgB\n" +
            "{)}l[}C}3e[eYjya]axxyx55xjYt)7v)CJL?//c!><rr;c*!!*z?z/**/zsz/*r!r!!c*cccc!+rrccr+r*c*/**zzsTs*=+*/*z?veJvv{IFv?==sItI||}C()Tz?zccTv|Ji{C|?JCf3i{InxZulCI(}i{}iiiFt}QQQQQQQWWMMWNNNWWNNMMM00MM0g000g0ggg$\n" +
            "iJIun3tlt[neo5ZY5xj5yxa]y]][FCF(LLJJ(7)*!><<;r*rc/L?z///zzzz*c!<rr!!rr**rcrc*cc**c/****/z?sLc_=**!!z*r?7)J3i|7>Txa]]]2E]axYliJsr<+!L)|ii|vLsiCiC1oxoutC}|Ci{}ii|{1mQQQQQQWWM0NNMNNWWNMMMM0000ggg0g$0$BB#\n" +
            "FJf[n1Iutlu[onYjeYxZY5aaa22n7Jz+sFz+>;/,<;<;=ccczs?z/z//*///c!<+r*c*cc**cc***ccr*******/?ss/_,!c+<*zc<<z)|C(vs|5jj]]2aayyjYoe[f(z+=;s||i)vz)LJ|ClZyeuuC}FCi{CiF|C1WQQQWWWWN00NMMMMNNN0g00g0gg$BBBB$g#B#$\n" +
            "FJf[nu1unuoeneZxZ5Yjxa]]22]eT1}|(()J)J)J|/,,=c*/s?/***//z/**c+<+cr*rrrr**c*c*c*/z/*c**/?z?*>'+!+<!*!c<+<){{)s!Zx2]2y]]E2yxZZn[tf|z+^>7}(|T?sLsTfuZyonl}CiCF}CF||}8QWQWWWNN0g0MMM0MM0gggggggBB$$$gB$B#$#$\n" +
            "(J}[nutun[u[[nZxoZ55Y]a]]]e</zc!<;^^^==rs7T/=r/z?z/*/*/*c**rr!+!!!**ccc****////z//**//zsz*r'=!>=+rc!c/;!/iiv,n}F1onyyYxjj55ee[tC(sc++|Cv7J/?TsTv[Yaent}iC{|}{F||1NWWWNWMN0g$000000g0BBBg$BBB$$##B#$$D#DD\n" +
            "((}[[ut[ueeZneZjZZ5jy]]]2asrsLr!C3unnn[[I}c;!>******ccccrr*r!++!!rccrccc*/////z*z|(*LFJLsc<'><><cc+*!*;c{i|*L|z/)CYY3(TT{onCnt11J*!<,7|7|v/+cs?TCx]o[1f{C{F}{F|7$WNNNMMMM0$ggg00g0BggBBB$B#B#$D$#D##DDRR\n" +
            "7(}[nu1t[n[nZYyjyxYe5jyy2I)/+:v{}33t[neenn1C<^+!rrr!r!+!!!!+<<!cccc*c**/*/zzz//z)(sF7Jv)|v__<;+!!+,,<+<rF||/Tz;!/1yYo1C?zLt|3fI3IT<+_v|(|TL<<!/sLo2e[1fi}{|}{(F|0M0M0M00gB#BBggBBBB$B$$$$$D$RDRD#RDRRR88\n" +
            "7|}nnu1ut[t[nYjyy5Zo5Y]aaFc?_c|i{{{CI1u[noooeol|<<<<<<+!+<+!+!!!rccc!c/*/zszz*/?77L?ssJz+?-^;>+;^_,++;+s()(?enexPe[nnYY[t3})J1fII}z^>s(JJ*zz<^;/Liyen1fi}i|}{F|g00M0000gBR$BBBBBB$$#$$$###D#D8RRRRD8RRmm\n" +
            "((}n[uf11ulneexyYeYZYjjxax*><'c||(||Fi{fI1lu[[[[uu3!>><=<>+!r!!rrc*cc*c*///z*z/?s/*r+c??c=->;>=:_;++=;cc)(7naS]unt[3ejE]yYZenul3IfC!;+7J7F>crc<!*ifnnIIF}i|fCilg0000g0gB$8BB$BB$B#$##DDRD8RRRm88m88H8mHH\n" +
            "||fnnt3IIln[5xxxyyZ5YZZaay(,/+->FFi{{FFi{Cfe5on}i[e[3}r=;<+++!!ccc*c*cccrc/*zzzz**c!++<=,_-==^_,+!>==r?I|(LZxx1zCC^}3ejxo[uut1I}}CiJ<<*JTJs!=!+<z7ennII|f{|}{(BgggggggBB8RBBB$$$DDDDDRR8R8R88H8mHmmHmHXX\n" +
            "|i}n[t}31lZ55YYjjxyyxZZ5YZYc/*/='(|iiFi{{iiffIC}tCIuu{|?+<<+++!c!rcccccr!!rrc***!+>==^,__':__:,+!<>cczLLiTcenx]j5elloxZont}}I1I}C{|v!;L))7vT+!==<JZn[fI(IiFfF9BBBBBBgB$RH#$$$$##DDRRR8m8mm8HHXXXXXXXXXXX\n" +
            "||}n[1CI1[Z5Yo[n[5yyaYZ55Y5js/*/>_'=7[u}}t1FfCi}C7utInmRmKVV{pml!ccrccrr+!+!!<<<<>==^,,_''___=<<>>c*sFfFv//te7Ls7iI[n[uuu1}}113}{iF7J;*JCv?LTL+=sTYeu}I(Ii(C[R8mmXXXKKUGGbbGGOOpVVV4444d9999996h66hh9hhh\n" +
            "(|Ce[u}lltno5neZZ5jyxyy55jy]]xy5[*^!P#$#DVi|iF7||)C|v{2PGAUUX8mGxrcrc!!c!+!>>=;=,==,,,:'.-:'^==_''-:->)C{L>{3)(T?/csLFf}t3}I113I}F|Js,;77Jv/!*rs??5n[C3|3i|CUKKAUUUbGOV44494d9966hh66kh66kqwSSSSwwES222]\n" +
            "F|{en1}f1nZ5Y5nnZYayyyoYja]]SVVKxx2OD$$#DAeJsJsvTLJJvv?z/GXKKApVDxc!<!!!+<>>==,,____:_-..'''_!=_!c/=!{7}{*cTt[}77|FC}f3}I}f313fC{|JvTr(()vJTcc++zLZe[{3(Ii(C{j80QWQ%%&&%&&&%%%%%QQQQWWWWNNNNNNNNNNMNMGZZ\n" +
            "i{7i[tf31onYyyjY]aayaxZYYakSa]yyhGK8$$$#mpa[IJ+<>=T|Tccr!pKKAUGppSq!+!+++>>==^^,_,_:'''IZn13}|{{F||<zT!<vvr|ZYYyZeu13f1I}}f3If}Ci7;(7(F)T7)/?rc</?xeuiI(fF|CF|7IC{J}i({F(|}|Jv)J|f|vLC{(F)L}F(7|}(zT|UO9\n" +
            "i{{L1Zxy]2SwwwwwkwwwwSSSwPSwSSSwShKmR#$D8pwanF!rnq9hqZ)rTKKK8AOpVp5e+<<<<>;=^,___:'--}Zn11Jue{777Jvv<*/czJv<o5n[uult[lII33I}}CiFT*+Lt|T?zvcz!<=>+{on[{f(}iFCF(73{iJ}F7{F(iC|)Tv)|}|vL}i7(Ls}(J7FI7]KwZee\n" +
            "F{C7(7F{}II3IIIII3IfI3SPuI331111[UXm8R$$$$Bh6w2E]SSwwkKBgBB$#KUbpVp9/<<<>>^,___:''--'I[17LI]jtF/*//zc<c+c+L}71}{{CC}CiCC{ii|(JvTL+!)VT*c**zc;=<cx2Yn[{f(fiiC|(73i|v}F({(({Ci)Tv)|i(sL}i7(TT}()a9O3k4dwkh\n" +
            "|i}CLfteZYjyya]22222ES2EEEESSSESOUHHXXHHHHHHmXXXXXHXXHXXKKKKKAUbOppOh<<>;^,,'::::rj$Ge|ZoyY37T?s!!+<^:!!=kKhxT?/iFF|(7(77777(((J?!^?S<>c<<*!*=!xEEYnuif|}|ii|(71i|J}F7{((CC|vTvv{i(ss}F7(Lv}7J7KA$g0gM0R\n" +
            "FiCCJF1exya]2ESwSSSwqwqwwqqqkkk4GXmmXm8mHmHHHm88mHHH#8XKKXKAAAUUUUUUG+<cx9XGKKkUmPKXHK}+<rccc*rc+=^:-:rcAx(7)ie({{77((7(7(FF|(7(sc;s7=<!cr*;!oSSES5n[i}|C|{i|(71i()C|7{77C{|vvvv{i7svfF7|LJC(77p$AUOd6hk\n" +
            "iiCC|7[52Sqk6PP96h6w9h9h99999996xdXKKKXXXXXXXXXKKKKKKKKKAAAUUbbUUKXVxwU]AGKAH2pdmm49PxI+=<++c!*!^'.'',cXGkTsT(Ci3I(7(|F|FFii|ii(Ts!====^,,rx]EESSS5nui}i}|{i|771{()C|(i(J{||vTT)Ci7?J}i7(L7}7Jqd4p466PP6\n" +
            "mUKXKAUHmKKKKAKAUbGbVpOGOOO44dddV96P6hXmKXH8HHKKKXKXXXKAUOp44dhPw2xZ[ta9qXA8KpOUqbdZn[/=>++cI;-'_*GRR8mUbbq3T|[o1}i((F(7FFiiFF||77TJvL^!|xx22ESSSSZnli}iC|}i|7(3i7)C|7{77{i()J))}|Jz)}|7(s7}7UGDUAbBOUUU\n" +
            "8GVhdOKmDR8HHXH8RRDRKpA8DRApdqSkH#8mUKHDRHmmAURmxHmppVOm8REV8HURRGD8V488K8mAAVXZkyCZu*z+r//3xnpH8bHHXXUGUKpFtoZn1Ii(F|i{FiiiFFF((|(777vrTC3nESEESSZ[liC{C(}||J(f{J7{(|i7(CiF)v)J}FJz)}(7(s(dBgRmBRggXKXH\n" +
            "u[[nnxSq9VGkEEof[y9w2S]jykd96ejV99ayxnZjpUU4k6AAAKp9blhSa5xhqq}6ww69peS5Ii5qEaYti}{}JzT)?*Yj6KKpdOKUKXUGAAUqZ5x[l}C{C{i{Fi{iFiF|i{i{iiF(v_Fuyx2EEEo[t{C{C(}FFJ|}i)|i||F7({F|vvJJfi)zvf6XD$g0$BgggMNDNM88\n" +
            "]]Zl|7166aZo55nZk49qSEnftS6jaaw]w4hPZyd9qYjnZy6KH4jU]x29axqnZnqqw3nkSjYo]txaI]C}I3|Ts7F7)![VHKVhdbbUUUUbbUAb[ee[t33}iC{{|FF||i{C{{C}f}IIC2dkayaEEEe[lC{Ci7}i|JifFv|i7|F7(C|(vvv)IF)svVGbRmR0MRDg$B8AgHXm\n" +
            "j5oe[Z55awhh[C]hSxen5nea9hp49y1u]]ya2yxqpOdy6KKb2[Z2aePE2ZaPi6oe5jp2flnZl[qIklC(vJsi?({J|>aKAOd9pbbKGbUAGUAKOfoenl}fC|FFiFi{iiC}}I33u4dhw2]]axxyS2entCCCC(fF|J{}|vii7F|(|C||TvvJI|vzCUKX0DmKXKm8BUpp4VGb\n" +
            "55xe555owwww22j5Ywh]ItSdq5[[neodAUpq5tn699pbOVdhj]S]1xEP[whaZyd1]Se5ojlioyIPe3iJLvc|*L$#$KXKUVd4ObKKAAUAUUUUbO35[t[tCFC}{fIfIf31SO4hwS]2EqPhhkqSaEen1C{C{7}i|)}{|v{i7i(7(C||Tv)7IFT?(4pbbKAR$gN00gg0N#KX\n" +
            "xeel[ufIaq2wyxZ]h6]2E]jYakk5|nESanY]y]OObbb6xZE52yh5x6aot62YZk2aa91Y3}3ZnfknnZTzLTcD$Bg$B##$g$VpGAKXKAKAUAbbbU4lY5eennl15VOOO4hh6qwSwkP4OGbGOhjyy2e[1}i}C7}i()}{|JCi({F7|C|(vv)(IFL?(4OVdppKbGKOOmVb6h]h\n" +
            "]2aj2yZlayY13ll2PPS]ae5whw]]]ay2k9kIxPhwxaqkydAy|aya]]xxSj[IqaqopEZexCuu[ExqqqLJ)C#$8ADBgBXK8XpOAXHHHKAAKKAV{FCSObGpVph966PqqqqPkkPhVpbGbwOOO4d66wx[1}{}{7}i()f{7v}{({((F{((T))ifFLzi]d$Ob9UVP9Odh4phUhS\n" +
            "yxa5en[ZYwauj69k]xou}lZ6qSaEao2p46wqaSkOpk1Zd62aEOSfS]Ey2ae2yZ[66xnnl[}1Y5enno[YZE}fs(7(|(7F7(bAKHmmHAAUV[onulnYkPPkqwEqqkkPkhkhhdVGUObGGGUAbVkkPaau3}{}i7CF(7I{7)fi7i7(F{|JTv)|f|sz{59GDOAhmdOdOm2qK#j9\n" +
            "2S]xyxyjj5x]ya]PdPjnEPhkx5eIt[y46h9w]6Ohk9qk2adhyFakdPuxj2]w2nkyZIZn3}3fy1JTssT3Zt{33}2SyaSk6qPbHXmmKA6CdUUGp4496k6Pkk66k6669hd4OGGObOUUUpPGddh6PSEYIIif{(CF7)}CJ)}F7F(7ii|JTvviC(szinkkUDG9UqwAOS9Kx]K9\n" +
            "25nIfI13le2uiuSwS]]]x]2hqk5nw9Vqxy[1nkVpO9Sx]q6w2j5ya]}l]k[k]q6hY]t311{j5[e5yaZ5xtIItI(J|FI1tu31o5jaq]2h4ObAAUUGpdVGpV49999dd44Oxbp4VUAhS]PV4dh6P22afCi}i7CiJ)f{))CF7i(7{i(vT)vCC7ssitkhShO9dp6wPpjxOhoP\n" +
            "e55yy2aye}5ZxqPkE]j1fewwxYY2]w6VdjZ64Vkq21}uPUOSEeZ6P55[uyyei5kEaY[lt(I|J77(77|7FC(?eZn3e5xuiTL{Itlnej]ex9dpUAAAUG4UUbOpGppp9udUph9pA4EykpOphPkkqayjx((v({{[ZZSbkxEKqICnyC(vvvvC}7?ziZp9E964wdh9PwqSZxVo\n" +
            "a2]]aayYo5xuqan[eoenxh9P2]o3fa92w]6k6Vp4k5]6k]5n}FfyEyZ[2]euu3u2x|11(1(5olieJvvTvTL?TsLCJi31e[Zjxyyyxn[1tl]hh4VpGUU9AUpKp9U8aU466pUbPxxhGOhwEqqq6yxYYvTzdi3v>!I1JU$KxSGe)h|vLJY}C(z?{ZVP]hqd9wqk2]j22jY]\n" +
            "]a]2]aaYxjyyY]k61C5k6a[nnoeoqhPES51uwVPS6ESxZy5j5Z5on[uI3[Z5l56w3[CJ11355tTiCvcc*sL(KKGGKksss)F{{CIu5yY[I3le5P66dddphj]jlayy6qEUGU4EuqO9E]SPhkqw2yxYY5akr!L)ZZ77{;cYxcc7;JPY{lY1[24dxxy5qk6VhP9d4VO49q6V\n" +
            "Sw222YZo[[[[5wS]]ajjjwkwtIyk6aZoZoY6PkSwxf{tej5Z[uttt11I}}I31l35n53vi|[uYFE)vsL){{3aAGpbUAbUJvY{(F{i({3111I3uekhhkqSS]5q8bbC245UOky2q2y269d96kwSyxZY2y2I=^5]5t[l*_=,>!^__^!S)J}i1]qGppk5w]S2S]Edpkjhya29\n" +
            "y]ya]wS2y2]Yeq2]2nnEqw2yyayja6Vx}ah]Y[ZZnuoySajx5x5YY55n[e5YeoI15l{(|ltZuy4(3Jv7T|1[[VVGOGUAXK{?v|feZ1ff}1133ll69pp49ZXfaeY[k]G9SeYjakdV4d6kwEa2owyeewo<<,3fF|J)7C,.-=-.^J7e{JvC71[1xSqZ6][5Enxqw22PSPyw\n" +
            "]E2222yx5YZ5YyetIlakkEaEe[Yq9ESwEy]qq2f3ySS[n}}C}1Ci}ii3t[eexeZ[[|C7FIe5edl<iTLs)i1LY4pOVObUAAX3iICJ|7{Cf{31If3tdOUGOAyf}eptUpw][ey9VGp4hkSSyEOEe56u{io5+^_c<;_^_'--`'.-+5YoZiT))(uIFI5Eoe1Yxt5ja5ZPywYj\n" +
            "w22E]]yyjjxy5Y55wPkyyZ1I3[SPSqSa5wdhEEyYo[eZ5ZYZYoen[e5Yn{{}1jettnC?(in][w]LT)Ls)JTl]6OOVppGUAAAAvF}fCFi{{C}II3tuqVOKVP[3[AGh]Y1j6h6q]2]2]2jEq2p2tC3Ie5ul_,,'-''^><</-,zjC{o{iif|ivzLJuy[l3tn1lunltaoYla\n" +
            "uuutt131u31lluyYyS69k5uy6hP]xYutYkwESao1lnYaj5YneZeo55ZZxya]a5]]C5ilnx}{e2q>7S)Jissny4OVV4VVpUUUAAH)JJ777C{C{C3I1lEOVGUUApkaxun52qkqkqkSowqXEGZ3IexaI{51C*c?=---.-'-?JzLJ)Ts/z*zz*F(3T{u1enee1otu[1ouuuS\n" +
            "ay2S2E]2aaxn}|l31]qyyYaxxEShkxY49waYeIIC1tne5oen[eoeYxya2SaanuuFnoY[tn3J}]ZotLTYt11lok6kqqk69VOGUAUUAAX7)(}fC}}}1ttGKKG9kax1|t5whd499]wS4b4P[II]PSeywk5nI((}Jz;_,>C_',Tc_<--':z)T9y1I+ctPXYV]Y6Yn]YI5x[n\n" +
            "E]S2]yjyxxxYaxxZS6w]aZIf5wq]]SES6dh]tex2xyeoe[tuut[l555oneu{uuxxj1i[fT7}ky[}F|FCCI3tYw]xjxj2wqk94pGUbUGG{v7{{CC31t3wdh2a5nZJ3oE6hPPSSSdRmG5lZk4wS[5EkxyonZ[|Lsz**sf}>_-/^.'+iJ*{/zzs[uyiaEqkEZPq4q9Sa2Y2\n" +
            "S2ayxx5jZjjyxZY5y[utuney9kk2SZ3jPyxjoxeeZ[neeooYj2EqwwaZeoujZx23iji)LfCv]j[}iFFF{}}fu5n3I3f[e5j]Sk69dVpVd|xeeDgQpAkX66q6dHXAU8RGdjw6dVUd1[ZhA5xp1i(vJE]5x17{|LzT7TJs/+'(>J(s<!+!rI*}f(sTPOdkkd6kqEyxnnxZ\n" +
            "5eenneeee[u[tII}tw2iu2Pwnel[Z5Phqq2ZtF7iC3ttun5Yy]Yx]2Ekx5y5I|o3{T(II7]qxx[}|(7|iC{|iI??vJJ77CCfueeoYSayECCtYSXBN4SadS]qpg8KOdpXUnSjOb3[apA5EdV]n3ivJYeItF{nts/ccr!crr?(u<;=!*?In{[377Ic>KSa9V6qpkE6w]qw\n" +
            "]y22aS2Ey]yy5utte]a]Z5xyqqa3t9qZlul1IItuoZZZYaawEE2q2Zy2Ziix3|TL|I|5jn3SaZnfF|(v(JW|zL%;* 8rg>tTQCvZe77|NC_JfqX8#e%XA%l#9DRHppKOUSKdYlxP4axUAy9]jn3Fve21vIT/c<r<=>>;;+*zf??sc!(zxJtLz7}tv3A9y9V6wq2wqSqS\n" +
            "5yYxxYZooeZZ5e5Z5xxoexS9Ey]]]]2SZC)(|tnoen[llu5x]jeko3i5f7TsT7f33tn|kES2y[u3i|J|||/QS*cQhz3Qtv%QJ{CtCiRQ[q[1aYKewE&%NN28P#O99OUpq6yt5qAeaVKyq9YjYn1{7LnL/LL<>=>;=^===^<sn3I}z7f1s3u|!+!vT=F4PEd9kw6aSkaE\n" +
            "]ya]aEy]aayjaxY[I{[It]Syyy]yoq6axjaxjyEw2wl[()7J77((sT//)Ji}I)yu3ZwEajqS]etI{F|||i{i|)T+(I1t11IffffIff}3tnZY550%Qkwm5Y]qdX929K495IjxP]jApYaYSa5ntf{Js+/(^!+<>=^,===^==><{L>>)[(7o[7c/^__='^<4V266kdPkh2k\n" +
            "1l3utul13u3lt3uu[nuYwSjZel1tekEy]2]evTTT(|JsT/z??ssvFiC}|JJY3n(w2waqjxwaaauIC{iiFiF(vL/cC1ll[eoe[t1333111f1l[nZW%W[oY52GRBR$Q0x3ny95aOd2S5Ynl2n}{|)/:-iFCT,!=<>>;=^^===+s(31Cs+;;;7[*L<=>^=>!GO94h6kw6EE\n" +
            "qqk66w]y5Z5ll{|itnyyxqSEZ[xkhSZ{u?7vLsF(J7T7!7)Tvv7J7J(Z12oJ5aq2jwS55oeyn]ot}{}C{{FJ|ftenu13}CCCI3l[oY5Zn1C1[nZn4&NaYnxwX8BW535edjxAVSpES[enna]t{c,*ssz!(,>=+>^;=^,^=^=;*)Lzcc!c!Tv|L*<<!>^+cTVOp66p9hhq\n" +
            "ZYxZY5yooxE2]y]axZenl]YnZyZ[u}(Lz?zTLTLJTvTiz|/?s[FltI)7o5xxyje55yanutxuttuu1fff}i|ss7It[[n[t1I}}Cii||7J7CI{}lntIa%g6jq2Z9A1oxqeeGqS6yyeZYnt3Y[}sssss*+>=>=<^==<<^=^^===r|Xf/*)*ccsL?)/+<>>+k[1]hEwkSEqa\n" +
            "aye[3}{i{i}}f[Zo[le52a5<++<<>>>>><<++++++!!!r!r?L7T|}IIf31lu[oYeeun1ItZl33t[uu33I}It[ul1I[YyyyjxxYoeeen[[utIi3YYxjaUg8#Uy1[y6a5pwwqxkya2xuuyYnt[n!+cc+>=>;!<,,-/cL+',==^<7Jv+z/z/r?Jvz/?s+c()YYkPq6qkPxS\n";
  }

  private static String getDefaultInvertedAltAscii() {
    return "hd2]x2]ExjeuZu13t[31utu1IIlwd94OVwwSwkd4hV9VObGVKXXKUAbbUbUGVH0#UAdpUAAKHHm8m$BgBXUAUGppp9PSi{{{}3[Zn2oS69SenawhpphkhaoYSPP9qEhwYwdwnZxy]a2SS22kP2xnxy2]PSq62wkkP2awwx`    `...-.....-.-''''''':':':__^,\n" +
            "Pd2axw]2ajeunuuutt33eu[tuu1]V99pVwwE266UOpVpOUKOKmXKAUUbAbKUVK$8#g8XAXAKH8mg0g0RXRHKUUbGO44d6SoIC}[ZnwZqhhSe[yq6VVdq6ao5w6h6kSV2xk42n5xy]2SqE]Skw]5nxjE2hSqk]wqkk]awq- `   ``..-.......---''':'':::_::,_\n" +
            "69]y5SSaYZuutueu3III3tt[ll1y9dVpdwwEqP4dAAOKXbKGXmKKKAbbAXXUpKDDm8RR$0M0MMM0B$#RmmXXUUUbOOVV4V966t5ZnqZPh9So[jwhpp4qPxo55kwqSqpyxk4jn5xy2wwq]EwkSyonxxE]hEqk2qkkka]wS-    ``...-....----'-'-'''::::____,\n" +
            "qd]jYEwyaZnunZZeuII3I[t[l3Iad9VpVwSwqhVVGHKb8HKAmHKAUbGAXKKVOAm#RmR8R8R#BBBB$##Dm88mXAUbGGGGGGppVd6ZZkohh9wZnyq6yYaqh2jaE2]5o2]EESPxoYj]SSww22SkSyenxxE2hEqk2wkkkySq' `````...'..-...--''''-'::::::::,_,\n" +
            "qd2x5ySjyYZe[enne11tutIlIIty49VOpS]2Ph94OK8XH8UAHXKUOpUKKAbOGAX88mmmmRRDD###DR8mmXKXXAAUAAKAGOOGbGO9Ywo999SZna2EkpGKKA8HObbky2P]h96wESa]a]wS22wqExnexjw]hEqk2qkPkjww` ````...''.-------''''''':_:____^_^\n" +
            "kd]xZSS2aYnuunne[33I1ue[[1IYd4VpdkAmD$B#KAXDm#UXmXAUObUAAbbGGAXm88XKKXmm8R8m8mXXHXXXKAAXKKUOVOUDmGGp4EZ994woakdU#gRRAUXXXKOXAGGOO9hkkqSqqSwSE]Sq2YueYxS26Sqk2qkkqyE>`.``...--:--'-----''':''':_______^_^\n" +
            "q4EjYSSEaZYZ[tt131uutl[nut[j494dwdObUUXHRRHH#XAmmKbGUUKKAUGUAAHmHHHXKXXKXH8HHXXXmXKXAAKKUbGpOK$mAAAUbVZ9VVw2kVb$$G2y266Sw64pbGUXKpV6dkqw6G9SE]kq25unYjw26SqqSkkqkyE..`.....-''--'---''::::_:::___:,_,,,=\n" +
            "kd2YZajjy55Zone[nut[tu1ItI3YkwPhOO9dhhdKHRRRDHKHXAObUAAAUbbbKXm8HHmHXHKKXXXXAXXKAKAKKKAAUGGOX0#KAHmUKXb9ddaq6hRpu133IIf31l[jkdGX88mO46kk64pOkSkwaouoYjSS6wkqSkk6qa!......--'_'':'---'::::_____,,__^_=^^=\n" +
            "PdEY5a2xjjxYe5[lo[ue[[313II59dU8GkU8D#AgR#RD$XXXUObUUUAAAAAAXmR8HKKKXXKKXKKKKKKXKKAKAKAAbGOA0BHKmRKbX88bd6wh4O6nlt33I11ttlueZYE6U8$DGP6kdVUdpd6SjntZYxwSkwqqwkkPSa......--':_':'::'''____,_,,=,^,,^,;^==\n" +
            "PdE55xyx5xoZ5Zeuen[lu33III3opaSP6hd9dd496Agg$XKAObUKKAUAUAKKK888XHKXHXHKKKKKKKKAUAKXAKUGUGARN8H8RmKHXR8R4qq4GmelIII13IfItune5Yy26U8$D9S66pbGOGp2xnto5xSwkwPSwk66E+...-.-'':,_::::':___,,,,,,,=^^,^^^;=;=\n" +
            "h9SY5xjx5YxYYZnlonnn[313IIoRAUXm8#BB$$$HG9VA#HAbGUAAAAAKXKAHHmmmHmAAKXXAKKAUAAAUAAKKAUUGUAHN$HD$8HXmKA#mAkqVB5Skae51t[ullnnZoYywhGX886w4hdAbpOpVY[1Z5ySkww6SwP66y'---'-:'_,^__:_____^,,,=,^^==;=^=^^>=>;\n" +
            "hhSYYxjYxZZe5Zeleenlt333I1GHOOXmw]x5Z55Y2SX#mRKAKKKKKXKXXXAHm8mmmXKXXXKXAUAUUAUAU6hKOPdOGKRNDRDRXK8KmKDXqk6Ap6UA4w[[ahppqo5w5yaadKmRg969PVU8KObVwu3eYyEqSqPSqP6h=--''''::_^,_,____^_,^^^=^=^;^>=;>=;>><>\n" +
            "hhSY5YayY555e[tltu[Znlt1I24A8MVqS]]yY5ZZZ5aw8$8mHXXmHmmmmmmmR8mXKXXKXKKUKAUUUUAb4hGPhdV46V00RDmmm8BgR88HPPPApUDHAa1[oawGUOy6a2]]2pR80V66PpO8RmUOOeIoYyEkEq6Sq6P6_'_:_'___^;,,,_,,^^=^=====>=<;<;=<;<<>++\n" +
            "h6S55xaYyYyY5[l1tneon[311kXG0X6kqqqS2ax5Zooooej6R8R8RR8m8R8m8mmmHXXXHXAAAUGUUKUG99ObOOdU8GWB#RmDB0g88#mGhd6GZ5olFZY55[[Yy]E49aE2]SUBDG6ddKUURB#UOktZ5aEkEq6SqPP,__:__:__,>=,^,,^^=^====;=;;=>+<<<<>+<<!+\n" +
            "hhS55xEayxjZZZut[Z[e[llu1uKDRNX66h6PPqq22ajx5YYYYY]mRDR$RR8mHmHHXXKXKKKKAUUUKUUbGAAXmXGbX$WD#D$M0Dm8#DXX4h951}3xZyY]ol}3t[eo5xj]22wmDm999PDXHKRHKk2Z522kEqPEwkx,______,,^+^^^^^^^==;;>;<;<><<!+++++!+!!!\n" +
            "66E55ya2]xZYnuulttn[[ee11thBU8WDPkqqqkPkqw2Z[eZSkYZYaSX$#R8mmmmXXXKKKKXXHXAKUUbUAKXH888$B0W$$B0g8mD##Xb26hOeuuybwwBS]ZlleYxxya2SSwqd8RK9VdGm$H88U9ZZ5226Eq6Sqh^,,_,,,,,^+>^^^==^>;;;><<<>+<++r+!!++r!rcr\n" +
            "6kS5YyS]yje[nu[llut1uen[[n[XUKA#N6PkkkkqqkkEE2wSyw]xxq6GmRR8m8mXHXXXXXXHHHXXXKKKHmR$$Bgg0NMggMB8mRDXXUOpkVXZ5l3lnoxxeueo5ySS]a2Ewq6Vm#O4d9Vpmm#$8deZYE]h2kk2k7^,,,,^,^=>r;====;=>><<<<++!!+!!crrccrccccc\n" +
            "6PS55aw2aYen[oY5Yn111[nn[[nlGAKAD0N$9YxSSyaPEwqSw9xy25!>!*vTqL!xHXXHXXXH8HmHmR888D#$BBg0MN000$RRRDXKGkEkVUAyZhOG9k]Y5YxYxaEEaa]Eqqk9d#K9SVbOpO8$Gp[ZxS]62khwY<++!rcc**z?sz?sssLTTvvvvv)J777777((|(7777((\n" +
            "66SZYxSjjy5enZZennltuttnnlt33u1nYKBm|===>vk6kk9664w6VqfFs*z/c<!?uXKXXHmXmmmRD##$B$$gggMNQWMN$#$0NMWMND4wqpDqa4hVbAXGOPEEy]S2aa]2SkPdGB#99dVAHKHGbGn5YS]6]q6wz**/zzzz?Lv)))J))77(|((||F(||i{C}}}}CC}}ffI3\n" +
            "k6qZ5aSEa5enun55e[11ttout133}vv*uuIL>===>/Z9OdGVpOdd4VbUUsc**/Lv>uXm8mHHm8DD$$gBgg00M0NWQNNM0m$0mKA$mq9SqAKVyYE99PkSSE]S2EE]a]2wq69VpXhh44dpKX88UOeZYw]h2khwql<_.-``   `    ````....----''-''''''''':?ee\n" +
            "kq9q5yEayeZ[ttlu311t3ln[u3i}1311(s*+===;!T1Y]98RD#p6pXXXHTc**zsLTC{mmmm88DD$$BBB0gg0NNN2eZa]SPqwP668UpHRVVH6n[[teZxy]2a2SSE]]2Swk9#h9hP4ph4AGHX8Abuoxq2hEP6wP6h2wqdSkhqkh6E6dV496E6VOwqhk4OSPhh6EhUp6zL7\n" +
            "kqwOanut3ICCC{{{i{{CC}}}C|}C}}}C}(*!>==;+TC15PmH5i7(ie4Xp**c<*LTTTnom8R8RD#$Bgg00MNWWSe5aadxZw99hd4VRKUKU948onZ5xYjyYj22]]2SSwkPpKmpy6pGUVXUHR$D8weZYqEhEkPwPh9]qkdSkhqPhkw64p4dPS6VOSkhhpGShd9P29I*CeZZ\n" +
            "Pqwhh9PqS22]]2]]2]2E2]C|x]a]ayaa5zc!+<===^^(|CffI}}CCi*^,^^==cz?TvL7ARR8DDBg0000NNWNN]YahO23lykAAAUUXRK8X8OS9aSqwwwSwqwwwqk6hdVpOmm4vpAKAKUX#$RXuI[5Yq2h2kkw6h9]k6VSPhqhhqwk4pV4PqhOOEkh6ppS6d1Js]iv){i(\n" +
            "6kSwO2jZn[l1t3IIIfIIf}f}}}f}}}}}szr!crrr!!!r!rccccrrc!cc*****/z?LTTL(R8DDBggNM0MMHt=so6eet[]hpGGmmm8BMmm#i*(upbAkPk66hh99999hhhdbmBb}RDX88KmK$mu}}[ZxkE6EPkk6h9aq6dSPhwhhww6VpV4qkhGGSk9hOVS9dh*/=,__:_>\n" +
            "PkwwdPaZu113ff}C}}C{{C{{{{{{iii)sr!!c!+!!!!r!!++!!r!;+r**cc/*/zzzzzz?8RXu7rs**iz!F*c!*S88XXXXKHXm$B0NMXX/uh9dkZhwq99h69h9hPk6hhhGXDO9$RmXHK#meC}}}n5YkS6w6qkPh9aqhdS69qhhwq6VVVVqq9GVEPh6OdSh99T=*/L)|7i\n" +
            "qkwS69Y[IC{i|F|7|(({77J7J777777(uJcccccccccccccc*ccc****////zzzz/*rTl{/I*sc/rITJ+!)7Fl28$888XmKmBNWNNBXrsipGp6wk]2h9hPP6kPkk6kkhVGH$$$#BggXuI}}}}}nZYkSkS6qk6hhaqh4SPhqh9wP6VVVdSk9bdEkh6O9Sh9{J)T)((FF|\n" +
            "+z*cc*z!!c*c****z?szTTL?ssLv))JJv7|||(c!cc!+!rcc*c*crr*/zLT)vJ(FCIueYj17{c*+cTLz{?Je5YA#D88K2#WN0As<<+!z??{]p6YeySkhhPh9PPkkkP6699pdVpBH6luII}}}}}e5jkSkwPSk69h]k94w6hqh9qk64d44SPdU4S69hGhS9zs>//z,Lzzz\n" +
            "+sT7JL*!><+rrrr+<>><*T/+></TJ{Cir=+!/*!>>r+!/z<+ur!TLTL!+<fT+!/<<?>+T)++c+!//vreF1SnxKU8HAAauZTr+zrrcczsz*Tkjoe5y]qhPPkqPkkkPPPhh6h999VHpw]Zf}f}}}e5jkSww6SPPdhEqdhqhPk9hwkP4VddEPdU4Sh9hG6)^_>!^<_,cccr\n" +
            "xYYZ5uC{Jv?i}foE5t7{I}3ltiJJ|Zlv7711u5etT/z)i|*//*T7zj7C1nu7{iS|CC|7TZ}n]qn{f1[yqEqEdUp4bKul|ccTJs*z*czs///{enuYjSwwwqqqkkqkPkk6kqkqkkPhVgPxtlff}}oYyqwqwhSPkd6Ek46k6Pk9hqP6VVddEk4b42(c>=__^^,,,:'>':+<\n" +
            "I3nj6hy|(1nonnZei)Ji}f5Ey}(t33C3C)7Fet)7{[lZet(*rvtz3lI71ui5eZ{iCa5iCt[e3yl123wS2]6pGhk9dHYTr*T(J??zzzz??z/?YZZ5y]]SkwqqPPP6PkqwqqwSEE2]wIJi1t1}ffoYxSqwqhEk6dk2k46kh6k9hw6hVVVd2k4G4vs?>!<_:<>,=,+*,rc!\n" +
            "lneZ5enn3C7(Yw3(}uZZn5Z17(T)Jtax33t1Itl{Ts)t|*c?IYnI1Z|ffe1Fk|oZnlLf2x5nx5{2ijwh4dOkb6qd6D1*/sJJT?z*szz/?z/*LEoZ5jSEw6PPkPkqkkwSE2]axv)(CI331uu1}IZ5ywwww6EkPdqSP4kkhPPh6wP6pV4d26VUwz*r_>+*c*++^zLT)vs?\n" +
            "[nuonn[eCCCCfIt[[{(I]y}J{n5YZZoJ/zT{[y5|7JTzLvJ(lI}3au}|5C(1e1JaI}Znoljket]FZ]kdOVX6Kp===cc*/v))L?**////zzzzzL]n5jYywPwSw2222E]a}sv({}3I}iF(7F{}1fZ5ywwwwhSq6dSw6Vqk9qh96w66VV492Ppb6)T?z**<=,'__,,_';*c\n" +
            "uZZxYx221{ICtle3(|3I}It[1Fi[65f}15[3t3Lsz??(ue}nIt(nu|1oj|IueFf11JauaS]e52FZZepUOpX;=^,=^=;^,=vLs**c*/*/z*??z/)j[nZZZ5janvLLs)((|{CC{iFvLszsL7lt1IZYaEqSw9Sk64Eq6dSkhqPh6w6hVV46]PObh)sT)TL*?s*Ls!v?|73(\n" +
            "3I3tItnx11[a]jjI|FCI3onC(CI3311fiJi2u|7Cu1{i1J/1P1t13IulClY]{1{eT}eZuwxxYfu{{{O9dw==+/>^,,c*+cTs*crrr*/***/TqkSCL?sTvT77|||{{{{FiiF(vL?szCLLL)J(|ClYaSqSq9SkhdEw94Sq6qhhPw6hp4dk2POUk3J=s?7zvF7sJ(vT(z(}\n" +
            "tl3nZZYn[C3xl|7i3uexEje|{}1f1oITv|C{1CFsTiae)|f1fL}2}Iftf3ZItnY||u55jYSa[[o5ZeY[n}S2Gh9h6hhP9h?/*r!!r//zv5e5xj5[iFFi{C}{{iiFi(F((JTs/L?sssz/?viiF13x]SqSkhwkh92q94Ek9q9hkq6dpV46E6Gbqn7s>L/(!JLJL!I{c;lJ\n" +
            "I}3utltttnu3133FJFl5}F(iu[Z]jY1)|77CI(s7i7iiI1J(tP1FJFxutI3{I5F1e2n5]SaE1adpGGVaeyw]]SI}t1}i|{F?rr!!*/|SJzzsT))7|i||ii||i||(7(J)L?sLzLz/zTFs)J(|F}f[22kEqhSk9dEwd4SP9kh9qk6dpVVkw6GUk5ii/>s7z{C/L}7*u3c7\n" +
            "fn5222y]joIYkxCC}333l3I(iFn5{7T{utYy5ivLL7}lI{|{Itnt13SxIFYiI{|([3y]aaqlnYZnt1nnuj]2y2hd6P]ayx]aent1i3I7)Lz///zsL)TsTv)J777JJ)vLuzT)vz/(}I|TvJ(||fI1EwqSk9wkdd2qd4SPhkh9qkh4p4VwS9GGkyF7}(L7JT|C|LluL7eF\n" +
            "Znnt1f1tZE[eu{Fif3laEoCCl[[I3C|vJle()vi{IaEx|zL}fZe|Fn[Yxttoknif1[Yjyh]6d99h99PhPwhbZe5]Z[uxkppw2yjZZl3Zu7)Tz///z?)/z?sLsTLT7xJzT(7T/)}1iTLT(FFii1tluh6VhqqYee}ziuf*i2wZtw6VVVVwS9GUkeT7f7|)CJ7JFCi}elve\n" +
            "1II311tuonux{1ZYZoZ5u77FI3e]E37I{I|i|vT)in3|F3n5SPE1ftnYfIZYxaYfu6aahahnejkZdVVVVVOGpGOwdq]yZ5nlu1t1u5Yyyx3(7)vL?zz7//TcLJz<1z)||Tz?Fuu(ss(C}{{{|tl[[VpUJka4Dm2adz=*u}sZ4(6VOd[SwhbGwevFI({J7C{if3lIIl[I\n" +
            "313I311[ut1t[Ii|ywnF|3YZ5eoei(|f}[ax{v|}|f}unt[tnenoZYx2]Ye[j[({]Ywday]nnypkwVXKKGOh**sscFGOG4Pwqw2xnt[Y2axZ[F||J))T(t3lj1t1|{fzsz)}xiL7}3}F(iiCItlu[[1iHHp4eeh9w#X[lXX9#dF[qj[aYI)Jultn{i|T7F7JvTL)7{|T\n" +
            "}CIII[no5YYYn{}331tllCiCj2ti|1eoee[||i}CuEwyZlneYYyyyaa2ES2ayj]nZn]4q65Y[kf4VGpdqw]1/sT?z/zzdV[qhPqq6q]aaa]]xZi(7i{}C3[{<??wI)[zLi1I{I1I|7J7|iC}tue[ItI2#Bn3ny5xK0#BDHB0g$mC4dSka3{?TLin{3}I}IfJTil7t1I7\n" +
            "t3t33C}ItI3[ZiI3IZZ}i{ftt1tt1|vuS173uYneZxot}1lunln[[nn5YZn[Ze2anjq6Pxyext)h]dV9V6yY5vv?ssz/ccqbV62ZeaEESaaaaxx(JTTv7ecE1Z[YFIs7}Z[t1iJv)J|iCf1IoCtZZCoRRBa2P6949wBWW#WWB99Zq9Vw9yYau}{e(3Ynf5u{CII|}FtC\n" +
            "3fIIIItu[[e[[tZy2j3iif1fZY[{7f}Cft3i{IE]t}}Y5SSwSawkEkk]yYZouoeYY6S9P2onZJxRkpOG4kyp[)TsTL?z//c]k2wdP9qwEq]a22ayJLz?L*tESoTjzT{3YZ17TsT)(iC}1fLfZ[|xqkon8$0XR#0B0MWWQNWW8[[oekp4d6x2P2[foZa[lynl1ne|tC[l\n" +
            "CIff3311tlutn[[nCFit1ea]]5}F}{}1[CJ7f}t[eYZenn[n[eZ5YZn[5qqSalZyy5wbhk53YC3pp4pG4dpj3|sLTLTsz////VPSEwPkqqSS]2ajx{Ts*vFYaY*s(3[al|(|{3I3I3IlfiITIjwa2Znxj0ggNWNMBD8RAWgUtwqewkk26kVUp9x15x]y5axYZxy3o[j3\n" +
            "xxxyyy]yx]yjjYt[t}|7inxt|(F3u[xy[i{}}1oyx5u1tn[5oeoonnenut131n33Snkj5uSqZI{D9C49qGG5tvLvv)vTTz/z//!ddd999wwwqw]2ajfLv?/z/Li3ux5nI{F{Fii}eC{cfsna]Zu12wnawAKb$WWWWWNWbdUpd4pGUUKUUAPhapqxaZ5oZaoyx5yoxxxC\n" +
            "1tI}If3I11uZE6j]a3{tt[1uu}}7iu[)7C1[Z]2waj5ZneZ5YZeo[ut1fC315xYP5eu5y5]dS3eoypp[jayjoi|i{{i|JvLsz/zz/*c9dhSEwSSEajjscc?Ji1ua6ynC(J)J73{})zvFY223|}Z1{i[Z2hhSdU#0gDw0NgpXg8WWN0U4p7ty28XyFr[v3[|[53u]nlY5\n" +
            "f3}I3tltuuu[1lueC|C31n22nC{33}f}(J(IjolIutZeZYyxYjYx[nnoZoYqxYullyk52V9EFtYSP6PwS2ay[C3ululIC{F7)Tszzzs?q49qqwS]ay]CJ(f1nZe9ao}|(FF}}}J>+?nxni)C}YnfFute5nY6pGUKAGESR0WABQN8qdKwAUUGYxtq1}iF}nF{)iJ}1I[I\n" +
            "Cf1tuunlelltln[nt5xyYZZt7iiI}e]l|tutouZZeY5Zoee[tff{CC3eZoxteuIaklq4OEw4ItYSkPPPqSE2xn5]2]2YZnlI}F|7JvTvJ6uZZ>,`T/ic||{|J!c//+<s)lC|Jv/Ja5e(/[uTaqhV9}Inua9w6OUp9pdGA8NhD9hGRHmmH]KE26GpFsJiiJ|i{ftu55ue\n" +
            "noZ55ZZooYxYy2]SjCIkxIFC5ZxYe[F({{IeyPhqw]yyx5n[t3[u3ffFuntn]6o]qph]29I{llYE6hh6kwq6k2GbVdd99SwExZoe[}11fwSy[}c^'vC1J}I{T,<cLJTcz5}ts?a53T/nf)v35]qVd[Z2yPqZjGAXXHmXHXbhY8D$mKb25qY]992XD*}17v|{TFf(C3{C\n" +
            "3tIf1CIft3ttnxyyZ313enut{{1]yJ{ejxxa22yxoeee[11{}fI{IetInkku]6Vp626[lZ]}3e52P664h9-6UO #K%<H_Dyp.wVeo996'w09E{c+;o`c/`x;7>>!TT*Lz}*)[xu|)1lz/t73lZ]PVZIyV2pUXRH8$DD##8KU2GbOKH6UudyOUhSjV]/717v|{iIC{}i}\n" +
            "[1uuu[eoeoeenZnenuueZu}7}t33I3f}ewd6PyZoZ5YjjYnu3tZioaqnE9pGphE]]yZPif}It5x]k6d66PA.}KK.(U].yV`.9qwywk<.5{Yy1[*o{f `'-I+F=L77LzT{|tyn{*o1v*t{7[l[5aq9OZOAOp8D#D#$$$$$BRG5]2SU92yG]x6m8mVV#P)FfJJiC|3}i1}\n" +
            "It131ft311tt1uuY2q52jI}11131o{|1ul1ul1fCICxY6d9d99hhOpAU49qS]4tx]e{f1l{C3oy2qP6P6kqk64p862ajaa]EEEE2EESay5e[n[:``iC!n[3iJr7I7*vJn2luFIl/T[1[}3n5yEwdG8AhBmmRD$BB$$$B$$DRqpDD45hheY9KUB00$NBRvvI|(FJ|F(fi\n" +
            "aj]xyxja]x]jy]YxY5YuC}teZjayZi}t3f3oVppph69GpAbbbOOVPqwSPdd[]5hCIC1ituC331x2wqkkPkPh4OAXwaxxYZoZYja]a]aaaEaj55n- -Yo[nIs>,<=`_l]5tJn3LJI}n[Zjf5Eq6dA0Wkkwpgm$8DDD$$B$$$mOh]awG8DD#hYAOR$DB$Rm?L7)(|iC|f}\n" +
            "{iF||CItne[jjq6qy5ttu{CfeYui(}ewxb94OOPh99V9H94pVV9d9dheaIed[1{flC}nnoZtZ3oySwSwqqP9PEjoZxa]ESSS2]jYo[[e5ywaY5eZv '3[ZuCc+^-[]no)lu/v}Tf}5o551IyqXgKGGUHhgD$8D$#$Bg$$B$#A4pUKXmXmpV6OKRRmDB8KpvLL|(T7(({\n" +
            "e[ueuntooufIIt31leZ5x3[ZnteYxShOUbUpOpOdp4pkU6AbOYPxj249o[lutlZn[t15xjlxyyxYyEEESqPGG9]jY55Yya]ESSkq669dhw2wSx5y21`,|l{In7/aou{ZZs{C|ttZe[5ya[YSGOOOGK8D#D$8$##RR$$BB$$$X6cEAK4KKXGpb4A8RDDmFYa3(}Ci}f{3\n" +
            "1toY]SqkqkSS2YeoYjZ[I3n888RRDDDDDRR888888mmmHHHbOhp6S222]axYYe[Zox5a2yeja]yYYxa]2E2yYxjy2Y[ttttul[oZZZ55Yxy]k][[ul1z,+;ztyY1|1nTCC{lit1Iuxxtu5jY5m8XX8D#R#mRggNAXO8Ng$$BR9d48UUbAHbdVUAbG8Xhd[[iF{|{iFu}\n";
  }

  private static String getDefaultMkAscii() {
    return "]a2wEy5ja5n5jYyyotu961*+Luu{}lneon3{f[u[uuu[nu[u[e[[ne[uuli!==,_:'-..--:^^,_:-'^^^^==_.` `,<;>+*)7v{J^._v(>-'rJ(*,:=*F1ir,zV89;` `'=c*+ccL||(T!_--....`..`.-:__,,,^^,:=<,_+{a5exxyyYZ64yZ[If[xS25t3is!;;\n" +
            "EEwwS2yjSS5Ywxt333l]ppxFs{u1t[eenu}i}uu[nnntun[[n[[nnnnne[i+^^^_''--:_'':_,_-'_^^^^=^_.` `^<;>+cv(C[jl*_*?=''+vJ/,:;*i[F!,+Ie!`   .'^+++rTCC7s+_--....`.`..-:,,^^,^=^+c/='X%&&MewGPpQ&@&QMRe3ej]xn3|?**/\n" +
            "h666kwwEEw5Zqkyo5YZ56Ud2t1[eoonnnI{7CleeeleennnneenZZZooe[Cc==^,:'=cs*+^__''-':,^^^==_.` `,>><!!Ti*,v]]/:++_'rTv/=,+L{[}!,+Lc`     .-':':!(C)/>_---........'_^=;=^=>;^<!=.VN&%#[EOUK&&@@@%&Ru}ltluu|?*c!\n" +
            "9h6PkSSE]]2yS]Ya]aaZSAAqxE4kZeoZoI|731eZ[nnnneeZe[eZZ5ZZ5nFr;;=,'.'=<<;^'-''--:^=====_.  `^<+!!cvvc_'rJL+*!::!sL*=^c?v3f/^<TL.`   `.'---'<zLs/>_'--....--.-',^^^^^^>>;==<sv|t3teE699kDMMNND]un}I}FJL/c!!\n" +
            "k6kkqSSE]a2]]2SkhESSPbXh]Ek2Z5Zoe3FF}l[n[enn[[oZZoeoZZYxYoCr;;=^_-..--':___,'',======_`  .=++cr!sv+_.';;!/<_:rT?<,;cTv|v!=+(?.`   `.'---'=+rr+>_'--....----:,^^===>>>>;=</sLJ{Itoy]x]dVVa1CF3}v?zzLL**cr\n" +
            "kkk66wwkkqEy5oakqSESpH8b2k6n}n55etfIItnnnoYoeeneoennejYY5oC!;>=^_---::___:,,_:,^==^==_`  .=<<rrcT?7J>--cin}{v(1{z*>cz*!<;=rs*`    `.''--:;r/*!^_'--...-----:,^^^^===;,:-:=>!L)(|(C3}C3lnYofLzc!rr/Ts/c=>\n" +
            "kPPkqwwS2yaYyxyYn31ZaG8Dbwy[unZZZ3I1neoZoneZoeeeoooenxjY5ZC+;<=,:-':>r*!;==,_'_=;====_`  .=<>+!rssF(7{w9wOG6l(xqEkY6dYic+>!!=`    `.'''':;!*zc^_'--..--'--'_,^^^^==>>=_-'_=!**/zLL(F)J73t3(!+<<>!zTz/*>_\n" +
            "qkkS]xne5[C(T/*+!zLvuEOXRG]2k2n1I1loeeee55YZ55ZZ5YoZ5jYYYY{!;<>=,'-_(oYnF*,'--:^==^=>_.  -;<>+!s|z/Cayee|)7CInI5qHm6Sl^.':=<>,-`   `.--':;+c*+^_'--..--'-''_,,,,^^==>^:-',^>=,^,>cLvTcrz*+;;=^=+**/*c+<^\n" +
            "fCiJsL)tyx3T>^=c?^^=cf2dKmGpGk1Ite5YoeoZZZZYYoZYYYYjyxyyjx{!=<<=_'--^>;c7T,':,^=>;<<;_- -_+*c+=^?Fxy3lILs3[5PXHbhu_` ``    .';/L*'.....-';!*/!^_'--..----''_,^^^^^;><^'-':=;=^^_,>LLc;;>>=^_':,=!c*cr!<>\n" +
            "!=;+!Tl]wkwYJrJ][J(ukko]pmOwV6n3eZ5Y5ZjZe5x55x5e5ZxxYxa]y5i<=>;^_-:_,=:'---+z+=;<>==>_'>r>,>svvL?J<cr+/t1{3)<=*7!.            -_</T*^'--'>ccc<^_'--..--'-''_,^^^^^^;=,-.-;<+;,,^=,,,=>++<>><;--'^c!**<^_\n" +
            "r'_!Je2]E62aYtCu5enYwp49pRDw2KqtnZYxYxYxYYjYjjYyj5jjyayxaY}<=>;^:.-:,,^^,::>r>,;>==<z==>;;>>>c?s/;,<T7i/*z:.````              `-,__>*>-.'></z+^_'--..--'-::_,^^^^^=<;:.-;>z<:``.':__'-:''=cs*;'-_<!+!=_.\n" +
            "u1CfCZ]axja]kh]y2wqPh4pOGDgAaGOxnZxxYYYxxyjjxyyyxay5jjyx]j}<=<>=:-'',=!*c;,=<_,,,=zz!,,<;,,=>++^_,,<>L^;- .'.                   `..-,<c,:'+*!<^_::'----'-':_,^^^^==;;=;,!s*=.  `->++^`  `_!Lvc<^=<<<<;=;\n" +
            "YZnnxwS]xjaEwd6xS4Vhh6669UR$B0RhuYayyjjjjya2yaa]]yyyy]a2ax{+;<>=_--',^;>>=^=_'_,/3T+>_,,__=><!+<:_:_'``                        ``...''=+<:_>!>,_,,:'-'::_-',^^^^^^==;+!+!*>_`   -=LT=`  `:<?zLL/c*!<;>/T\n" +
            "kkSP66waxYSq6dp]tYkdhSwEE6A$$R#Aoxaajxjjxjjyyaaaa]]]a]a2]jf+;<>=_--',^<<<>;>'_r3C/<;<_=_:+cz!,--````                            `....-''=+^-::_:__'--':_:-:,==^^=^=>!cc!z+_.    '<?/^`  `.,/LLszc+<<+!zL\n" +
            "h6P6hqY55Ya2Y5e5eFIYyxawEwV8BDmUyyyaayjy22]22]a]2]]22EEa2xC<><>=_--'_,>>>;,::ZkZt/?z+'-_+z<^'-```                               .`..`--'+s+_'''___'--'::'-:^^^^=^^,>!/sc;'`     :!L*,`  ``.^rLs*cc!><rTT\n" +
            "PPPwy[u3Tz7{(F(J317iue5qhqkU8m4qE]2E]aa]]]]2]a]22E2a]]]22]}c+++>_--:,^<+<=_!kPwZ{ss_''=!v/='-'``                                `-'_,<,,<c+^:'--'''--':::.:^==^^^,,;cz/>:`      _!Lc_   ` ``'+*/*!c>>rvs\n" +
            "E2yj[1ZY[YyxnY22jEe|CuY]66kGAOq]ES]22]aaa2]]222ESSE]]]2S2yf/!!+>_--':^cz!;sdqw25fL^'=!!!rr:-_:..`                               `-:^<!<>><;::-`.-----':::.:^==^^^^,>!c/;'`      ,+s!:   `   -;c*/+<>>*T/\n" +
            "]yj5tekkan5xtlaPkoeZu3njP94Obd2EqwS22]a]222]22E22]aaaa]22x{!;<<>^:--':^=^}9YPhos_:c*Lr!7L=<!=-'-`.`                       -`` `..',=+<;,=^:::.`..----'_:'-:^=^^^,^^^</z>'``     ,/s+'   ``  .>z?*!<>>/L?\n" +
            "jxxeuj]ae3{JcL(3x]u3n5[xkd4VdS5YZZonnZnooZZZZeneZZZZnuo[nu7<<<<<>:-.__'.1pqU4n/^/FsTs<s*>/!<^>^-.--.``...``````           `..-`-'^^>>^,_.'`..````....'_:'-_==^^^,^^=<cz<:``     ,zL+-   ``  -<?z*c+<>/T*\n" +
            "j5Y5[lI{)c>;!!;=;>!r?{uaqhVpk37vLvLTLvTLTL?sL/*?////?LvLsz!<r+!<=_-.'_,(hEqdSz(1vv|/!Lr_+!=;+>;=/+>_,_'-...```.```..``  `-':_,:',+c==>:.`-..``````.--'''-',^==^^^^^=<**=.`    ` =s?+.       -;c/c!<<>*v*\n" +
            "xjxoi?zc+!*s){1n5xjyZ3}[w69p4t>^!c/?c*z?zz///*/***c*ccrc**rrc!+<=_':,=1qyjSEvtnzT1+,=,:,_,;>=><<==+^<=^_,':........-_:.`.^^,,<<<<=;=,^-`` `       `..----::;;==^,,^;>!!=-.``   `=?L+'    `  _rzsc<<>;*s/\n" +
            "oYjx3J3a2anfi77}IneoZenZ2qkhg%W$bhjs,=r*r!=^,=>>>;>><!r*cc!cr!+<;,':_Ikao2jsffcvv''=-.-.,__=;;;>,:^;>;=>;==::':_^''_,^^_-,/<^;>+!>:.--..           `--'-.-_;>;;=^,,^>!!='-.``  .;cz+:``..` .=/Tz!>>><csz\n" +
            "5Yxntexyje[[oxy]jo[eoYES2w64$%&&&@@@WHPL^+?2Dk<-,=^=;<r*r!+/?*r<;^'-7S5[[3rz/<+<-':. ``.:^,_^^=;<+<==cs+>+><!;+<+,,<<,,=c^',>:'`''`   `          `` `-'-',^=;>;;^,,,+c>='..`   `^*/>' ```` `,c?r<>>>;cL!\n" +
            "YY5[[ZYYxY2wPPkP6kSxZyE2aq9dHQ&&&%Q%&%%QW#RVjL^^>+;;_,^===;c/z*r;_'LZtifT^_r+>_`..` ```^/z!+/<!!7iL^;?n//*!<LzL*<*^^;,^^,r:`..``                 ``  .'-',^=;;=^^,,>r*!='.`    `=?*;-  ``   ,+/r+<>>>rT>\n" +
            "x5ZeuuZ5YjSSSyeeoett[j552kdVRNNNNgDRR8mHKbOGqf37J?r>_'_,^=>zs?zc+^cou/Lc,'^,,:```...-'+iJT|7LsL*5yT*T)yf/sz!?v3n?c;;^_,=---` `                     ` `'':,,==;==^,^=cL*,-``    `,*/,.   `   ,c*!+<;;>rT+\n" +
            "yjetI}}tuu[ul}iiii|if3eYZhHDKmD8RmXUb4hk]Sw6d6w]uxgWNDbq}sc<!c*!^=i[s//c_;_::'...``._Je1yq[fli{}hhTzLn5qZ}fr?}|{nT!<=,_'.``                        ` `'___,==;==^,^+zLr^-``    `_c/_`   `   ,c*+>==;;cc=\n" +
            "ao[1ffIC{{{i7T7(((7||CCI[X$ApKmOS6SaybDWgpZi{u262eB%&&@&%%QBpi==<ru}<+<,__'-'-.``.-,7aahVqyEy[[hmKiz/[5S9ueI?iiz1niJ*=,'-..`                       ` `'::_,==;==^,^+*s!^-`     `,c*_`   `   ,cc<;^=^;rc^\n" +
            "2yoeu1IIfi(7)JJ7(77J|C3tA$UOUG92YDW0#MNWQQ%Mi366ez]B&&@@@@&&&&WR[((*,>^:'-..` `..:cfxyaq962jxkXRD#bt7|ehU9[no)|IL(Z[uCz_-.```                     ````',:,,>;;;==^=>!z+,.       ,c*,`  ``   ,c!>;=^^>/!,\n" +
            "qwj[[t3}}i|7v)v)77|Ctlnh8GOKb4qjGMN088DRmmRBafxxlJV#Q%%%%QQ%&@&%$i;;^>-.:-``` .>T7t5Zn{Tv{159Xmm8R8KmKbVKR89Sd4phI7oEy]Z7^:'-.                    ````',_,^>>>;^==;;+*<,`      `;z+_   ``   ,r!<>^^;<!<_\n" +
            "KPkwa5nn[[ufCC}{CtueoYqGppbUO9]oHHpVGAXmmHXXKKUm8j|aK5dBWWWWWQ%%o<z,,^-,:.``. +[IJ5EP9w2[IC{fe6AKm8m8RRR888mGh2yeI(iFJ7v|3ui!.`             ``     ``.'::,,=;;^^^^^=!c>_`     ``=rr'   ``   ,+c+>^,;+z+_\n" +
            "8X92wq2ay]aY[[llunyqw6UG6bbpdkkOGGKKKKAUAbUGOVOOVVOh]VUHNQQWQQ%mi>==<,...```.=2ZT|Cf1t5S9dwYltYPGKHHm8RRmUOpPEjYnnYjSpOp4qnuYnr-.``        `  `    ``.':_,,==;=^^^^;rc;'`     ``=c!'    `   ,*r<=^^;</<_\n" +
            "KHm6Y2PPPqSSajaa2dOdhKAGbAUO4pUAKAGqkGpUbORg$xCZa5{TGNQWWWNWWQQw!,=;;'-..`--`Fk2{J(zzF1xwpOAGSlekGKKXmHHKp4hkE2ESPqSS6hhhSxItSkC;^,:`         ````...-',_:,=====^^;<c<=,.`    `.>zc'       `^/c>^^^=+sr:\n" +
            "$Xm89eyd4966Pk6944pH#AGUKXKKpOKKOPkG0%WgBM%&%OT[6P3=6MWWWWNWQ%NY,;=_^,..``.-'a96kZCr<!<_T}[]6h9endKXmmXAb6SEq69dOGVkan1tnn[t3Zw6Y(!_`         `.`.--.':,_:,;==^^^^=>r(5Cz+_-   .<z!-    .+)sc++>^,,;!/+'\n" +
            "AXRXKGyEpOdd49ddpXRXOGUAKKAGGKbp9p0%&%Q0ggWWW0u(a21<u0NWWWNWQ%AC====^:..-.` )99Ub4]ICoJ=+<ixaxpV5qX8RRXbkx2k99hh9khhwaf(iCC{IjqhPSI+-         ``.--'--_;>^^^<<r!>=;;+r/v7F)r:``_+?*'`  _Jahwtz+;^,_=+*+'\n" +
            "|I6gNNWN$AGUUOOO8Hb9PdGXAAXHXUV9h8mHAXHHm$$D8UX8XDEJLRQWWWNWWNk*,<<___-'--``18DR8XH4u3yon2bXOPOK9VX888K9dVOPxntJz[v^r/){fI1t5qhhPSSe: ` ```````.-''',',;;_,3|}|CCs++;^*ybPZeY!-_!L!'.,v1]dVq51vc>__=!*+_\n" +
            "PqwqdRBWWNBmOVOAGXKADDAUAXHXKUOppbGVVdVpUAKGGOppOpa[{uDQWWWWWNa>;;^,--''--` 6M#R8mKbO4d99bX8RDXOVU8#DmbVUmDXGGqfc=;([|rL5EkVOppdhSa3:.`````````..'':,__>^^^;/2OEIlp8Gh[IYwKXXUf;+?!;su2hpAUOk]5}s>^>*L<:\n" +
            "9UDK]2hm0NNBO9OURHHDKdOUAKKAbbbGVVVO4wh4OAOh6kw222Eh44MQWWNNWg[>!,=,'.-'__.-pg#8HXXXXHAAAKXmmXOVOX8$#mAOAX88mbUpPEx25e[a4UAKAGV96Sx(''````    `..--,:'';;==^,zlPXX8X8HApS]qpUKU[*rvJu]q6pUUO66Sa[s<<**<-\n" +
            "gM0ggmd29KHXXXK8$DDbGOOppOGbAOOOV9hqwGD$$V2aEkVGOpkS]YgQQWWNWRC<!^=^-`:,=_`<GRmKKHHHmmH888mKUV44UH##8KUUUX88HXmHUbGUKAAKm88HKG4hq]ez.-````````..-':_'',<==;>=^=r3x6OX8#DmXp4dAHUkzvJ}ny2xnZjjyn37c><rL>-\n" +
            "g000M0RUAUbAXXHB0mVVpGbVdGUbbOpVkEUm8b46y55eennnexSx?JR%WWNMWA)=<;<_..:^^:`cbAUOGGKXH88888HUphOAX8DR8KUbUKHm8mKAKKXXXH8mmHHXUphqEZC;-.....````.''_:__,,,^;;;==^^,,=(]pKmDRmXUOAmH9nnykq9h6k6h2YnC7z/*z=-\n" +
            "gggg0MBHKR$RGd$0OppppVppbbUAbpk2tjGP5n133Cs*vL{Z|eqS}+A%WWNNW8ic/=+;:-;r>' JXUp9VGbUKXm88mKbqO$m8D#8HKGGUAX8RD#DDR8RRRR8mXAGO9q2an)^--``````` `.'_=;>=;<=;;=>==^^,=rcLZdUKRRmmKbUmUwPpOOGOpdd9PqE2j1(/,-\n" +
            "0000000mUBgBRg#p4V4VVVUKXXXXGkyZ)sJ?/c*/r>cChBQH)1Skf<9WWWWWW8{==+r:__r?<:`cUp96694GKXHHHHKUhdAmRBBmAGV4VGHRDDDD#DDD8mXXAbOpdP2yot?,-'``````````_cv7|7LT)iZtvr+=;^=<zc;!tEPVXHX4hGdS9pdV8#8Ad6k2axYofL,`\n" +
            "MMMMM0N$X8B$BDUUAUGGbKXXXXXU6yizL*/?z!zt6RW%QNM$i(]9Zre0WQWWWMo+/*!_<<sJr<.^dVkqwk66OUAKm8KP2ySbAUpV4VGbOOXm88R8R88HKUGpppd6kSayZ1z:-:.````````.__;!(nj2XRDDmmmmpa1)c<=:_ChhdOGV64Paq9dbR$#A6waxeIC{J/_ \n" +
            "MM000M$V]XWNDmKGppGUKXXHHA4qjCzc**z!(6WQ%QQQNNNMa/x9]sJ#QWNNQ%2^/+r+>*L)/<_.EdqSqwqhVGUUH8U2})v15Ytv*L3y6XXXHXXXKAUbbG4d9h6kqqyYt{+-_'.````````-_._-TaooSVOX8m8DRmXXX4ns=JPVdpGUV42f[aE9OOd62Zult7L*>^- \n" +
            "0000gR4]mN0BKpOGbUAXKKKKUdSY(*zTJ7)dW%QQWWWNNWWN9/e6S)c8QWQWW&mF<+c<z*7JJ*;.3kkqqqPhVVGAKKXKKKdj26dAAbO4KRmHHXKAUbGpVVd9d9hPPSynC*_-,,.`````  `'_:,Tm8GEo{v7[hKX8DRRmmXKP){S4pOGVwjf3t15]aYe13I3{s>_-.  \n" +
            "0000$AXgWNDpdAUUUUUAKbOdkauLcL1I7a0WQQQQQWNWWQNMVslkk}<AQWNM0QNZ<+*z*!JJs*c'<xPPPkkPhpppGbAAAK8RDDDRR8HHm8HXKAUUGOV4d9999h66kEYt(?::_'..```   `-:_'>x6w9GXhIc<suq9b8mmHGVh6w6OGGVw5i3e555xxYuiJJ)z<'.`  \n" +
            "000ggNWNDUUHXKAUbGGGV49Se(//TJ1qgQ%%QQQQQWWWWQNNm7fS6u>dWQW0MNWgt^_=;>s?==/,=[Eq6h6P69V4VpOUAAR#$D#RmmHHXmXAbbGGp4d4VdddV96qyYnr_:_,^:..````  ``---.;cLzLJf5SZ/=>z7[EGXXKUOdd9VOVqoJenn[Z5xZeli)s*<'..` \n" +
            "0MBMWBbUAHHHXKXKAbO46Sao|/z*TkM&%Q%%%%%%%%%%%QQ%MtJyqn+kWQWN0NWN#1>!^=!!+=^^-FawPPPP699h9Gdat1lSbdPVUXmHXKKAUGpVV4dddd9hhhS]xe|-.-:_,_:'.``  `..` ``'^zz/r*/LCYfr;!LYPVAXKV99VOGGOP(3u1{Iunnl{vs?*>'.`  \n" +
            "MNM8dAm8KXXHmHKAUGO9q]l|c<7GW&%%%%%%%&&%Q%%%QQQWNSTnk5cy0NM0B8mDB#ds`_+/!;:_-^ZqqkP6h9h6ku7Tz7i(T)7TLuVKbbGGbGVd6h9ddd96kw]ZxI:.:,==^::'```` `..`:/c!//c7{C|Lzz7{J>!)xwdOUGOdVAXHAp5/?v)J{1I(L/!>,-..  `\n" +
            "N0dGXmHKXHHHmGVV9hk]5T)]AB&&&%%%%%%%%%%%%%%%%QWWWb?lqys[gN0gg$GhKX$%BOxI<,*!_.TE6qPdVp4k}?++><!>>!<<+czuhpVpOpVVV499hhh6wE]x[>:,,<^_>::-`  ` `.-;Jv(i5VKKHm8RD8RK4j{r>)S9VUKAUAAAUU2JvLC|||F7T*+;'.``  .\n" +
            "O4pUKXKKAGOd6q2YetCJdgWWWW%%%%%%%%%%%%%%%%%%%QWWWK)I62TtBMgg0MHdOKDWQW0K5z+!!z5eEwS9VV4pd3/i]EYoICuIfv+!T]4pppppVVp4996Pw]aZL:,=^,_,-'.`     `..`.,Ca2694ObK88KUUUAAp]4AdVKmRXKAV99EI1ue5e3}C7T?!_``    \n" +
            "pUAKXKUOGp962YnJTYXH8MWWWW%%%%%%%%%%%%%%%QQ%%QWWWD|{w]LIBMg$$B$KAADNWNW08plzL)n2tEq6dpGG21nakOGVh44VpwuldmbppppVd4d9h99qS2oJ_,=,,^=^,:'.`` ` ``..-'',^=;>*(n2k944GKmXKAAKKHmmXKKA9]2Y3eyYot3t}J/=_``    \n" +
            "KKXXKAUUp49kY|sx888mA$NWWWQ%%%%%%%%%%QQQWWWQQQWNW$I|E]s1$Ng$$Bg$AbRNWN00ggNQW0g0huy299dV9Y[ZYo5]yx5e{IjOXmmAOp49hh9996kEytT;^,_,^,,=_'--..```.-,,^=^__,;>>+=^+F[Y9OGUGOGAXXKXHKGpw2Sj}tu[u}}tCL/=-```  `\n" +
            "KKXXKAAOVhxCz[A8mmmmA8NNWQ%&%QWQ%%%%%%&&&%%%QQWNWgZva])}$WM0BBg0Xp8WWNM00MNWWNWW0dk2P66P99[C3jwqkaYf5dbUAUbpV4966d44dPSx3z,_,,^^,_:_:::-.....'_,^_:'_::__^,,^_rtj[o]4GUUAKHmXUGVhPkS][ejEonZxetC/^.```-'\n" +
            "KKKKAbp4quzi9UKXmmXXKX0NWW%%%QQQQQQQQQ%%%QQQQWNNNgqfhkn1m0gggg$$RG8WWN00NWWWWQ%844dphPkq6d9hyt33tZqdpOOGGOVd9h664d99PEYi>,:'-'_=,__::,__-..``  ``....`...---''-znyY{i]4bUUAKAGdd9h66Sli}u[t}i7vsr=,::::_\n" +
            "KKAbOd6j){6UV4bX8Rm88KBWWQ%&%%QQQQQQ%%QQQQQQWWNMMm1Iou7JX0BBg0g$A9XWWNNNNNNNW$6jAdEGAV66kdpVd9h9hhhd44VVVV4d9hhhd96kSo)r!<=::''---'::,^'--.````     ```` ```.``</z/c?CahObUbp969hhPkwC.--''':_____::::::\n" +
            "KUbphEtv5UOUUGAKX8DggK$NNW%&%QQQQQQQQQQQ%%%%QNMNNX?_fnLsKNg$B0WQQWQWMg#$B$D8H8kVU63OAGppd4OAXXXKbOOppVV444d9h94V46kx}??cz{*-`.``.____,=,-.`..``   ````..`   `  'z*r+!JZq4ddVd9dd6q6Pqj_``   ````        \n" +
            "GOdky}JSUUdh4OOXDg00MmDMWW%%QQQQQQQQQQQQ%%%%%%%%%%NBB$DBWQQQWM0B#D$$D8RDDRm8HDUOP](FVbGOppGAXHm88HXAUbGGpV44444d62uJ?sz**(Y4aIz-`-:__==^-``````  ` `````   ...``+sccc/l6OUUUbOpV9kqkhw*..``````         \n" +
            "phwy|IOApGAH88R$0MMMg8$NWW%%QQQWWWWWWNNN0ggBggMMMNNWWQQQQWNMg$#DDD$Bgg$$#D8m8RBpfan*LEpp4pGUKXHmmHXXKKbbp9h6h6wyIT/z*/s*+T1jtab88ktv_'__-.`` ``  `````   `` ``` ./T/*cfE4GAKAGOV99qwEku-````.-````      \n" +
            "qEe|jUmXKHmmRB000$RBgBBNWWWNNNM0gB$##DRDDDDDDRDDD$Bgg0NNWWQQQQWMg$BgMNgB$DR88GBXp965svadPP94pGbbUUbbGpVd6qaYuCv???zz?)v!L{C]{T7C[Z6URX9I_..-.````````````    ````.cL*!TnkdbAUOOV9h6kwka*.````````       \n" +
            "2IoXmmXKHD$Bg000N00M000gBB$B$DR88HmXXXXHHHHXXXKXXHHmHR8RD$$####$000MM$RR8HHHHmV9a{Z}n3?CYy6k6944VVdd966x[}FTss?LTssvF7LL7ffluf{iiCI1eahmBmPC_.` -----``....```...`.=scri5S9pOOpV4d96kSw[,`````          \n" +
            "|ZAAUGOmDg00g$0bJ!LyA$HHmHHHHXKKAKKUAAKKKKKXKKKKKKKAAmXKKKKAAUUUAKKHRRmHKKKKAAKm0H(J(3iFv1eoYj]2]2]jxILz/zsTvvLLT|1o3]Ou{lyax2PP94OGbUAKR$g0MNUyT,` ` `--''--''---.-!TLv[oaP9d4VpVOdw2wh[,`.`      ```` \n" +
            "kXmXUm8Bg0M0Dh(;!;:-'(9XAAAUAAUUUKAUUUAAAKKAKXXXXXXXKKKXXXXXXUUKXAUOUAUGbAKKKKXHd9mVylI57/*zTLvvT))vssLvvvTLLvT7ZwVV9Px4UDXS9KR8mmXKKXmm8DD$gMWNNNNMBRD8Kyz^'..'---.`-*s|uYaPd4VV499999kVt:.```.```-``` \n" +
            "8XX8$00BD]c;+^,^:. ``._aXUbUAUUUAUUAAAAAAKKKKXHXKKXXHHmXXXXAUAXHHmHXAbbUUAAKKKXHXPY1vzZx5xv!+rc*?szz?sTTTLLT7f5]4pVHX#pDHywGAKHHHHHmHXR$####RmmmD#D#g000ggBg0#S*^```''';L3xykdddpVpGbbOV4Vt:-,<=:-.```  \n" +
            "X8$0N$d(csTc_-```      .xUbUUUbbUKKKKAKKKKKKKXXKKXKKKXXUUKKKKHHHXXXXGGGUAAAUAAKXXmKkSaF!Tn]at?rrrrz?szTzLJu]2S6pOXGqG$V]kbAAKKXXXXKAKXm8D$$$$DmXXKAbbOVVOGpVpbXR$Vi_```'/J3ZaS44ppbAAKXHAOG9qAW0}.````  \n" +
            "B00Ul!cc>''^.`          ,kAUUUUUUbUUUAAAKKAAAAKAKKAAKAAKKXKAXXHXKAAUbbUAKKKKAKKKXXXUyT(EIs//|xZiL(7c*J|feOd6#8UpSxmGwhbKKAAAAAAUUUAAKKKHHXKHmm8RmXbVphEayjxaayk4OUHDd|_.-=vinxE2khpOUKKAUUGbdHNqEq<`    \n" +
            "Hxr^;=,'.`              `cpKUUUUUAAUUUAKAAAAKKKXKAAKKKKAAUAKKAKKKKKAKKXXKKKHHXXXXKXXGw|*+f]uLC}?nPwjfmH9RNKbAyEgUSwVHXKKAAAbUUUUUAKKXKAKKXXXXXKAKKKKAUOV9wYu[ZYx]w6dGH8yL.<!zv3eShpGOUKXXK4OB9zu$2<.    \n" +
            "._rr^-`                  ^6KUUUUUAKKUAAUUAAAKXKKKAKKKAKKKKKKKKKKKKKKKXXXXXXXXXXXXmXHmKh[v/**JyuLa9kZEXbYSha594]y9KmXKXXKKAUbUUUUAXXXXXKXHHmmHXKXKKAKKKAAGphqyot}}CfnE64Kmpl/,!(Z]PVOAXXKXGG9x}qm5aXs` ` \n" +
            "   `.,`                 `-)GUbbbbbUAUAAUAKKKKKKKKKKXXXKKKKKKKXXXXHXXKXXXKKXXKKXHmm8mmm8KpPeI||i7ZE53eZePh9AKKXmmmmmmmmXKKKKKKAAAAKKKXKKXXXXXXKKHXXKXKKKKKAbGVwe}vT|1ex]64pKXVF!7[264GUUGdnFPAAIvkRlw7'``\n" +
            "                        ``'tOpOGbUUUUAAAAAKKAAKKKKKXXXXXKKXKXXXKXXXXXXXXHHmHHHHHmmmmmmmXHHmmmXXAUUHHHK8HmmmXXXXKXXKXXXHXXXHXXXXKXXXKKKXXXHHXXXHAKAKXXKKKKKbbbpk51|ileoxxYn[n[yV(vnYhb6x]dbZ{YSUd{TpD}_-.\n" +
            "                        ```^wkdVOGUAUAAKAKKKKKKKAKKXXXKXXXXHHXXXXXKXXXXXHHHHHHHHHmmmmmmmmHmmmHHHHmHXXXHHHHHXXXXXXXXXXXXXXHHXKHXXXKXXXKKXHHHXXHXKXKKKKKXKKAAAUGphautuC)TvTs?/c;IC{oywyPhyyS4ZZe2hGk}!:'.-\n" +
            "                       ```.')kYq9pGAAAAAKXXKAAKKAKKXKXXXXXXXHHXXXKXXXXXXXXXHHmXXmHHHXXXHHHXXHmmmHmHXXXHXXXXXXKKXXXXXXXXXHXKKKKKKKXXXXHXKKKKHHXKKXAKAAKXUUbbAUUbO6xCzcr++<^'--`-c/|3o2E62ey5Pt[ZYoZa9}:-.\n" +
            "                        ````<6w{YP9pGUUUUUAKAKKXKKAKKXXXHHHHXHHHXXXXXXHHXXXHXHmmXXHXHmmHHHXXXXHmmmHHHXHXXXXXXXXXXHXXXXKXXKAKKKKKHmmHXXXXXHXKKXXXUKKAAAAUAAUUUUbGph5(z+>_.````  -Tz}jS6kynj9VKKUG4hPPUj_-\n" +
            "                            _{A9iI]h4OOGGUUAUXKKKKKKXXXHHHHmHHHHmXXXXHHXXXKXHmXHHXXXXXKXHmmXXXXXKHXXXXXXXXKKXKKAKKKKKKKKKKAAUKXKXXXXXKKAAKKXXXXKAAUAAAKAAUGUUUUUUbd])'`        .,^+*|]dGKKXXXKKAGp4h9bP<\n" +
            "                            .<2O]L129VpOUbUAAKAKKKKXXHHmmHmHHHHXXXXKKKHHHHHXXHXXXHHXXXXXHmmXXXHHXXXHKXXKKKKAKKKKKKKKKKKKKAKKUAKAKXXXXXHHHHKXXXKAAKKAUUUUUUpGAUUAUAG3'              'ixaP4pbAKKKKAbOVddUd\n" +
            "                             -JS2ncCjq44GOGbUUKKKKKXXXXXHHHXXHHKHmHHHHHHXHHXXHXHXXKKXXXXXXXXXXXXXXXXXXKKKKAKKKAAKKKKAUUUUUUUAKAXKKKKXKKKKKKKKKAUKKAUUUUAAUUbbbUUUVC.                ,1jykVbKXKXXKKUUd4Vp\n" +
            "                             <nouZvc{Y69VpUAAAKKKXXXXXXHHHXXXHHXXHmmmmmmHXXmHHHXXmXXXXXXXXXXXXXXKKXXXXXXKKKKAAAAKAUUUbUUUUAAAUAKXHXXXXKKKKXXKKKKKKKAAUAAAGUUbGGA6c  `               .!e2SPVGbKXKKKAbppd4\n" +
            "                            .Jli(}|?ziykdVOGGbKKKKAKKXXXHXXXXHHXXXXmmmmHXXXmHmHXXXXXXXXHXXKKXXXHKKKKKKAAKAKKKAAAAAUAAAAAUAAAAAAKKKKXXKKKKKAKXKKUUAKAAUAAAAAGUbb9T`  `               `'vZ]k6VGUKKXXKAbp4V\n" +
            "                            >7v/?v|ss73aPhVGGOGUAKKKKXXXKAXmHHXXHmHmmmmmHHHmmmmXXXmmHHHKXXXXXXXHKKKXXKAAKKKAAAAAKKUbAAAAKKKAKKKXXXKXXXKKKAKKKKKAKKKAAUUUUGppOphv-``                   ,iY2k9pGAKAUUUAbVd\n" +
            "                        `-1['>/*zFeFss|eEkhdVOGUUAXXKXXXKKKXXHHHmmHHHHHmHHHmHHXXHmmmHHmHmmXHHHXXXKKXXXKAAUUUUUUAAUUKXKXXXXXXXXXXXXXXXXHXKKKKKKAAAAUUUUGbGp44dP7````                    =1j]k9dGGGAAKUbGO\n" +
            "                       /IUM04zz7CZoi|)(tySk9VpObbUAUAAAKAAKXXXXXXXXHXHHmmmHmHXHHHXXXXmHXHHHmmmmHXHHHHHXXKKKKKKAAKKKKXXXXXKXXXXKKKKXXXHHKKKKAAKAAAUAAbUbGOVdhhn-`                       `ruyS6dpObUKKAUUG\n" +
            "                    `CmWgggBBK|!fee3(7FuZSq69pOGbUUbbUUAUbUAAAAXKKKXXXHmmHHHXXXXXHXXXXKKKXXXHXXXXHHmmmHXXXKKKXKXKKKKKKAKKKKXKXXXXXXXXXKKKKAAKAAAAUAbGGOpV96S]z`                        `.?n]qPdObUUAAAAU\n" +
            "                 *e2Hgg$$$$#DRG3J1ut|||lnjEk69pObGGbGGGUUbUAKKKKKXHHXH88mmRmmm88mmmm88mmmmm88mHXHHHKAHHKKXXXHXXXXXXXXXXXXKKKKKKKKKKAKKUUKKAAAUUUUpGGGVV49k2a3-                           -7Z]k9dVGUKXXKA\n" +
            "            . _}KM0B#DD###$#8bVk}|t3{{CtexyS6h94pGbUUO9Pjot{()TL/z!+>>^,,,,>r*!>>>>>+c*sz?Tv))J(|i}flnZ2wqP69dddpUUAKKm8mHHKAAAAAAAAUbGGGUUUUUbbUbOppVd9k]]tr`                            _ixEh9pGUKXKAK\n" +
            "         '}O0DgNN0B$##$DDDRmAhSwEf3[[C1uuZy2k6Stz+^'`              ```` ``         ````                             ``.:,><!s7[qpUKKUbGGGOpOOppppVpVV4hq2Yu{,`                             =32qkhVpGKKKK\n" +
            "  ` `r6UH$%MBB000gDRR8mmmmmUhqPhO2Z2qwxeu3ir--.`                                                                                ``.,^r{24OVd9d49hdV9hPS2xn|v_                              `!naw69VOAKKK\n" +
            "  .vRQBBg0g$mADg0gR8mmmXKKGd9VOVApjqqxi}/..``````````                                                                            `-_-`   ._zIykqqSSqSaxZu})z:                               -)5aqhdpGAAK\n" +
            "'eKM00B$$ggBB$Bg0gDHXXXAUV99Vpph4Vy2l^._'.`                                                                                        `.```   ```,vtx5ZeZlI|7vL'`                               ,1xS69VObbU\n" +
            "0gg###D8ADg$$$BBB#mXUGGp466VG6wqk6wv,-```                                                                                                      ` `_sT?*/*TTi^-'.`                             /3xSP9pbGA\n" +
            "DDR88D8bdK$$###$$RUVOphkyY]9y}1I}7^'===,'.                                                                                                           .<zJ(vT,-':'                             -sYSP6VOOU\n" +
            "XXHHHHXKObmXXmHUUUO94k]5t}[us=_-..'::,:.```                                                                                                          `=???z!':'_:`                             ,}yk69Obb\n" +
            ":!FtYa2E22d4Vhqyu}3IF*<=:.```  ``.:''.``.-_`                                                                                                          .'^,:':_:::'.`                           `z5w694OG\n" +
            "__::'-----:-.........---``........',=,_::_:.`                                                                                                          .-__::::''::'`                          `:{xw9VVG\n" +
            "___:''''''''-...`````````....``..._c;'-',_::.                                                                                                          ','::___:::___:`                         .+I]P6dG\n" +
            ",,____::___''-........``......``-';+_':,^__.                                                                                                           `-___::__:__,___'`                       `<FnawdO\n" +
            "___,__,,,^,,:'--........`````````__--'^<,` .``                                                                                                          `'_::::_::_,,,,,_'                      `r73jEhp\n" +
            "_:::::::::_,'-......``````     `.=''===^_  ..`                                                                                                         ``,='_____,,,,,,,,,,-                    `?{[YEPp\n" +
            ",,,,,_::::_:-....```````````     .;L=^^_-                                                                                              `````           '7)/+;=^,,,^^^^^,,,,,'`                  `!}1eyk9\n" +
            ":::::::::::'..`.`````````  `   `.,,-`                                                                                                                 .+[}}((J)Tr+>;==^^^,^^,,`                  .7nZjS6\n" +
            "_,,,_''':'--````    ````````````:_`                                                                                                                   ./Z3113f11IC{Fvzc*+>=^,^><`                `'7yyEq\n" +
            "_,,=,,,,_:'-`       ```````````.-`    `.``                                                                                             ..              .1[tf1n[tl[[e[l133[iJzzc++.               ``.zuYS\n";
  }

  private static String get100MkAscii() {
    return "]2E5anjyou6*Lu}no3fuuun[[[n[ui=,:-.-^,:'^^=. ,;+)vJ.v>'J*:*1rz8; 'c+c|(!-..`..:_,^,=,+aexyZ4ZI[S53s;\n" +
            "h6kwE5qy5Z6dt[onn{CeeennenZoeC=^:=s+_'-:^^=. ,>!T*v]:+'T/,L[!+c   -::()>--...._===;<=V&#EU&@@&ullu?c\n" +
            "kkqS]2]ShSPX]kZZeF}[[n[oZeZYYC;=_.-'__',===` =+rs+.;!<:T<;T|!+?` `'-'+r>'-..--,^=>>;<sJIo]]VaC3vzL*c\n" +
            "kPqw2ayyn1a8byuZZInooeoeoonj5C;=:'>*;=__;==` =>!sF7wwGlxEYdi+!=  `'':!z^'-.--',^^=>_'=*/L()7t(+<!T/>\n" +
            "fis)y3>=?^c2KGG1t5ooZZYZYYyyj{=<_-^;7,:^><;--+c=?x3Is[PHh_ `  '/*...'!/^'-.--',^^;<''==^,Lc;>^',!*r<\n" +
            "r_J2E2YC5nw4pD2qnYYYYjjYjjyya}=;:-,^,:r,>=z=;>>?/,Ti*:``       `,_*-'<z^'-.--:,^^=;.;z:`'_':'c*'_!!_\n" +
            "YnxSxaw6SVh69RBRuayjjaya]yyaa{;>_-,;>^__/T>,_=<+::'`            `.'=<_!,,:-:_'^^^=;!!>` -L= `<zLc!;/\n" +
            "hPhY5aYeeIyaEVBmyyaj2]2a2]2E2C>>_-_>>,:kt?+-+<'``               ..`-++''_'-:':^^^,!s;`  :L, `.rsc!<T\n" +
            "Ey[Z[yn2jeCY6kAqE]2aa]22SE]22f!+_-:c!sq2f^=!r:_.`               `:<<>;:`---:::=^^,!/'   ,s: ` -c/<>T\n" +
            "jxu]e{c(xun[k4d5ZonnoZZnZZnon7<<>-_'1q4//sss>!^^.-`..```     `.`'^>,.`.``.._'_=^,^<z:`  ,L- ` -?*+>T\n" +
            "xxiz+*)15jZ}w94>!/czz///*ccr*rc+=',1ySvnT+=:_;=<=+<^,:...._..^,<<;,-``   `.--:;=,^>!-`  =L'   _zc<;s\n" +
            "5xtxj[oyj[oE26$&&@WP^?D<,^;rr+?r;'75[r/+-: `:,^=<<=s>>!++,<,c'>''` `     ``''^;;^,+>'.  ^/'`` ,?<>;L\n" +
            "xZuZYSSeot[52dRNNDRmKOq3Jr__^>sz+cuL,^,``.-+J|LL5TTy/z?3?;^,--             ':,==^^c*-`  ,/. ` ,*+;>T\n" +
            "a[fI{{77(7|C[$pmSSyDgZ{22B&@%Qp=<u<<_''.`-7aVyy[mi/59e?i1i*,-.             ':,==^^*!-   ,*` ` ,c;=;c\n" +
            "qj[3}|vv7|tn8ObqGN8DmRaxlVQ%%Q&&$;^-:``.TtZ{v19m88mbK8S4h7E]7:-          ``'_^>;=;+<`   ;+  ` ,!>^<<\n" +
            "89w2ya[luywU6bdkGKKAAUOOVO]UNQQ%i=<..`.2TC159wlYGHmRmOPjnYSO4nYr.`     `  `'_,==^^r;`  `=!  ` ,r=^<<\n" +
            "$m9y46P64p#GKKpKOk0WB%%T636WWNQN,=^.`.'9kC<<T[69nKmXbSq9OVa1n[3wY!`    ``-.:_,=^^=r5z_  <!  .)c+^,!+\n" +
            "|6NW$GUO8bPGAXXVhmAHm$8XXELQWNWk,<_--`1D8HuynbOO9X8KdOxtzvr)f15hPS:`````-',,;,||C+;*bZY-!!.v]V5v>_!+\n" +
            "9D]h0NOORHKOAKbbVV4hOO6w2E4MWNW[!='-_.p#HXXAAXmOO8#AA8mUPx5[4AAV6x'``  `.-:';=,lX88ASqUU*vuqpU6S[<*<\n" +
            "g0MRAbXH0VpbdUbpkU84y5eneS?RWNW)<<.:^`bUGKH88HpOXD8UUH8KKXX8mHUhEC-..``.':_,^;=^,=]KDmUAHnyqhkhYCz*=\n" +
            "0000UgR#44VUXXGy)J/*rchQ)Sf9WWW{=r_r<`U964KHHKhARBAVVHDD#D8XAOd2o?-`````_v|L)Zv+;=z;tPXXhd9d88dkaYf,\n" +
            "M00$]WDKpGKXH4jz*z(W%QNNax]JQNQ2/r>L/_EqqqVUHU}v5t*36XHXKUb496qyt+_.````__ToSO88RXXn=PdGV2[EOd2utL>-\n" +
            "00$XWDdUUUKOkuc170QQQNWNVlk<QN0N<**Jsc<PPkhpGAA8DDRHmHKUGVd996kY(:_.`` `:'xwGhcsqbmHV66GV5355xuJ)<. \n" +
            "0BWbAHXXAO6a|zTM%%%%%%%QMJq+WW0W#>^!+^-aPP699dtlbPUmXKUpVdd9hSx|.:,:.` .``'z/*LYr!YVXV9OGP31Inlv?>. \n" +
            "NdXHXHmV9k5)A&&%%%%%%%%WW?qsg0gGK$Bx<*_T6PV4}+>!><+zhVOVV9hhw][:,^>:`  .;viVKmR8Kjr)9UAAAUJL||7*;.` \n" +
            "pAXUG92nTX8WW%%%%%%%%Q%WW|wLBg$$ADWW8lLntqdG2nkGh4pudbppddh9So_=,=,'`  `.',=>(294KXAKHmKA]YeYttJ=`  \n" +
            "KXKAVxzAmmANW%%W%%%&&%QWWZa)$MBgX8WM0NWW0kP69[3wkY5bAbV964dS3,,^,:::..._^:_:_,^rjo4UAHXGhk]eEnxt/.`-\n" +
            "KAO6)6Vb8m8BW%%QQQ%QQQWNM1o7XBggAXWNNNW6AEA6kpdhhh4VV49hd6S)!=:'-':^-.``  `` `.`//?aOUp6hPw.-':__:::\n" +
            "GdyJUd4OD0MDW%QQQQQQ%%%%%NBDWQW0#$DRDmHUP(VGpGXm8XUGp4446u?z*Yaz`:_=-```  ``  .`+cclOUbp9qh*.```    \n" +
            "qejmKmR00RgBWWNMg$#RDDDDDBgNWQQWgBMg$R8Bp6saP9pbUbGV6auv?z?vLC{7[6R9_..``````  ``c*TkbUO96wa.````   \n" +
            "|AUOD0g0JLAHmHHKAKAKKKKKKKAXKKAUAKRmKKAK0((iveY]]]xL/svLT13O{yxP9ObARgMUT```-'-'-.!L[a94pOww[``   ``\n" +
            "8X$0Dc+,: `_XbAUAUAAAKKHKXHmXXUXHHAbUAKXXYvZ5v+c?z?TTL754VXpHwAHHHHR##RmDDg0gB0S^`''LxkdppbO4t-<:.` \n" +
            "B0lc>'.     ,AUUUUUAKAAKKAKAKKXHKAbUKKAKXXy(I/|ZL7*|ed#USmwbKAAAUAKKHKm8mbpEyxakOHd_-vnEkpUKUGdNE<  \n" +
            ".r^`         6UUUKUAUAKKKKKKKKKKKKKXXXXXXXmhv*JuakEbSa9]9mKXKUUUAXXKHmHKKAKAGhyt}fE4ml,(]VAXXGxq5X``\n" +
            "            `'OObUUAAKAKKKXXKXXXXXXXHmHHmmmmHmmXUHH8mmXXXKXHXHXXXXKXXHXHKKXKKbbk1iexY[[VvYbxdZYU{p}-\n" +
            "            `.)Y9GAAKXAKAKKXXXHXXXXXXXHXmHXXHXHmHHXHXXXKXXXXHKKKKXXXKKHKXKAXUbUb6Cc+<'--/326e5tZoa}-\n" +
            "              _Ai]4OGUUKKKXXHHHHmXXHXKHXHXXKHmXXKXXXXKXKKKKKKAUXXXXKAKXXAUAKAGUUUd)`    ,+|dKXXKG49P\n" +
            "               J2cj4GGUKKKXXHHXHHHHHXHXXXKXXXXXXXXXKKAKAKKAUUUAAKKXKKKKAKAUUAUbUUC        ,jkbXXKUdV\n" +
            "              .l(|zydOGKKAKXHXXHXXmmXXHHXXXXXKXXKKKAKKKAAUAAUAAAKKXKKAXKUKAAAAUbT `       `v]6GKXKb4\n" +
            "            `1'/zes|EhVGUXKXKKXHmHHHHHHXHmHmmXHXXKXKAUUUAUXXXXXXXXXHKKKAAUUGG4d7``          1]9GGAUG\n" +
            "          `mggB|fe(FZq9ObUbUUUAAKKXHmHXXXXXKKXHXXHmHXKKKKKKAKKKXXXXKKAKAAAGOV6]`            .nqdbUAA\n" +
            "      ._K0#D#$8V}t{Cey69pbU9jt(T/!>^,,r!>>+*zT)J|}lZwP9dpUKmmHAAAAUGGUUbUOpdk]r              _xhpUXA\n" +
            " ``6H%B00DRmmmhPOZqxui-.                                        `.^{4V94hVhSx|_               !a6VAK\n" +
            "'K0B$gBB0DXXU9Vp4yl.'`                                            .`  `,t5el|v'                1S9Ob\n" +
            "DR88d$##$UOhy]y1}^=='                                                      <Jv,''              -YPVO\n" +
            ":FY22dVqu3F<:`` `:'`._                                                     .^::::.              zw9O\n" +
            "__''''-.`````..`._;-,:.                                                     ,:__:__`            .IPd\n" +
            "___,,,:-....`````_-^, `                                                     `_:::_,,_           `7jh\n" +
            ",,,::_-..`````   ;=^-                                               ``      7/;^,^^^,,'         `}ek\n" +
            "_,_':-``  ``````:`                                                         .Z131I{vc+=,>`        'yE\n";
  }

  private static String get100Height150WidthMkAscii() {
    return "]]SEjYaoZjyyo3S6i+Le}}ueou{f[[u[un[[[e[nn[utc=^_:'..-'^,_:-,^^^=:` `<;+r)7FJ_.vv:'z(*_,*}}r^28y- `,crccv|(?,--...`..-:_,,,=,:>,,Lae5xaxZV2Z3I[yE51{s+;\n" +
            "h6hkwSEEeqSZ5Ye6bwtteoonnC7C[eeuonneenoZZoe[z==^::<sc;__'-',^^^=:` `<>!!T(^vEv:r='zv/,;Lf1!^?c`   `-'':/C)c_--......'_^==^>;^!=r$&N]EGU&&@@%QuItlu{?*r\n" +
            "k6kqSS]]]]Eqh2SPA4]wqZ5oeCF}un[en[nZZeeZ5xY[*;;^_-.--:__,''==;=='` -++c!sL^._=!*=:/L<^+T)7!;v?`   `'--'>!r+,'-...---',^===<>;=<zTJI3o]j]dOaI{3iszss*cc\n" +
            "kPkqqw2yYyyxnfnaARb25un5Zf3neZonZoneooonyY5nr;>^:-:>cc;=,_'^;=^=:  -<>!!sL|719wbpl{wEjwde?+>!=    `''':>cz!,'-..-'--:,^^^=>>^'',>**zLTi))ItIz+<>!Ts/r^\n" +
            "fC(sTFyZ7>^+?_=cuhKmpG]3tZYooZZZ5YnYYYxyxyj5*=<>_-.^><7c':^^>;<;_`-^*c;,?}y3uvslnPXKh*  `   `'>s*-...-'<*/<,'-..---'_,^^^^><,-'_==^_,!Lc=>>=,'_^!**r!>\n" +
            "r'<J52EkaYft5eowphp#b2UenYxYYjYYxjjYjYjyyxao!=>=:.',,^,:=r==>=,z^<;;>>/z/^;T7s*r-``.           .,:^*_.'!*z<,'-..---'_,^^^,>;'.;+!:`.':_''''>z*^-_!!!^-\n" +
            "Yonxw2xyEw4jSV9h669X#B0Oujayjjjaayy]]yyy]2aZr;<=_-',^;>^=_:_/f!>,,_,;<!+:__' `                  `..'_<<:;!;_,_'-':_'_^^^^^=;!+!c^`  -<T=` `,!zLzcc<;<s\n" +
            "h66hEY5Y2Yn5e|ZyxSEwABDKyyyayy2]22]a2]]22a2Z<>+;_-'_,<>=_:a5t*?+-'+/='.``                       .`.`-'+L,'':_:--':'-,^^^^,^!/?;-    :*z,` ``'rT/cr<<*v\n" +
            "E]a[tx[xynySj2{Cny6PdA4]E222]aa2a22ESS2]]S2Ys!+<_-':=/!r]qSYfz'=!!r<-_-.`                       `',<+>>>::-`----'::-^==^^^=!*!'`    ,cc:  `  -<*/<>>/?\n" +
            "jYZuy2ef|cJ}xy3nZZk4Vd]YZZnnZeoZZZenZZZn[nn3+<<<>:-_:-1V44I,/7Ls!)>*+^>'...```..````    `   ..-.'^;>,,.-`.```...'_':^=^^,^=<*/:`` ` ,sc-  `` -!z*c<>zz\n" +
            "xxYiz/+!?)I[5xyZftw644i=!*?c*?zz//****ccrr**!c!+=_',<]y]Sv[TTC,=___^;=<>===<=_,:.....`-_'..=^,<><==,_`` `     ..---:=;=^,,=>r<-.`  `=Lr'   ` _*sc<>;/z\n" +
            "5xetZyjnuoyajeeojS2q6$&&&@@WU}^r[DZ',;^;!*r!c?*+;,'7wu[i//>+-'- `.:^_^^=<+;=/r><=!<<+,;<_=c__>'.'-`          ` `--',=;;;^,^+r;'.` ` ^/+' ``` ,/*<>>;*c\n" +
            "xZeu[5YawS5eoe3[yZ2P9RNNN$RRmHKGGqf}J/<_:,^=*s?*+^)u*s,_,,'``..-'vJviL??5o*T{t/T!?)Z?<=^_=--` `               ` .':,^=;=^,=cL>-`   `,/=.  `  ,*r+>;>*c\n" +
            "aetfI}{{{7v((7(|}I[DRpXUSqayUMgh{{nq2y%&@@%%0pv^<?x<!=__-'-``.:7]]VSay[Smd?/o]9nn?fz1f7*^'-.`                 ` .':,^=;=^,>*s>-`  ` ,*^`  `  ,*+;=^;c;\n" +
            "qEn[1f}i7v)v77{tlS8VAb9yGNM88RmmBa3jl{8Q%%%QQ&@&$*>^;.:.`` ^T|eZuJvCn9mm8RKmA4KDbShph|1EaY7_'-.               ``.__,;>>^==>+c=`     ;*,  ``  ,c+>^=<!^\n" +
            "8AEwway2j[ulueSwhU6UGdqVGUKKAUAbbOpOVp4]pANQWQQ0i+=<:..``.?yT|C1ux9hjlnwGXHm8RmGOP]xnoxSOO4S[Y3^.``     `  `   `.:_,===^^^=rr,`    `=r_   `  ,/+=^=</^\n" +
            "$XR9nP4h6Pkd4VA#bUKXKpGKOw90%0BN&%wT6Ez6WWWNNQ%O,>,^_.``-'q6ke*<r^TfY6dynpXmHKbqEqhdOOhal1nnl3x6Yv^`      `.`.-.'__,===^^,>rC[z>'  `</:   .*vc+<^,=!/_\n" +
            "|tDNNN$UbUOO8K4PdKAAHXGdh8HAmXm$#8KHXXfLMWWWWW0s,+=_:'-.`1DD8XGunxnkXOhK9O888ddO9xeizt'rzif31569PEx:```````.-'','=;'i|{iC*<;^{b2[Y_:!z,.>C]495f/>_^!r^\n" +
            "9XR]SG0W0OVGRKRK9bAKKbUGVVO4wdOA96qE22k4UQWWNWR_!,='.-_--pg8HXXXXAAKHmUpOX##XGAm8mGGP]S5[x4UKAOd6EI'.```   `.._:'^;=^,vSXAm8HUS29UAy*zTu26pbb6P][!<**_\n" +
            "g00MgKAUUXX$0AVpbpdGUbOVk9m8O6y55en[ej]?2%WWNWq,<;+..,^->bAGGGXH888mApdUXRD8KbUKm8HKKKXXm8mHXUpPEe*-....``.-'_:_,,^;;==^,,L]bHDRXUbHHknywhh6PhyoCL/*/:\n" +
            "00000BUgBR0U4V4VpKXXXGwY)vs/c*r>Jh0M)ekfC0WWWWp-=!<_^/<->UV66dpKXHHXKhGXRB8AO4Vb8DDD#DD8HXAGOdk]oI;-- ``````_*J|(L)lnvc;;,<z!>tSdXHphGS9p98#Xd6SaYZf/`\n" +
            "M000M8]RQDHGpOGKHHHUhj(**/!(bQ%QQNNWask]?GQWNQW,/+/>zT/;.E9Sqw6VbUHmh}Lf55(*Jx6HXHXXKAbbOd9hkqSjt7__-.`````-_-_TxnSdK8mRRHXXh{=}pdObVh1[ahOp92nltT/>, \n" +
            "000$URWNbdAUUUAKG4kj{cv37KNQQQQWNWWNVs]k/kQWM0Qp<+/**|sc=<aPPkkhppGUAAXRDDRRmHm8XKAUGp4d999h6k2e(r:_-.``   .::^x6kGOnc+3qVmmmXVhq6GGV]}3o55xxu|J)!_.` \n" +
            "0M0WDbAmHXKKAGV6E5|//Tp%%%%%%%%%%%%QMiuq75WQN0N0#J,^<!+=^-ISP6P6dh9Owt1abh4UHHXKKUOVVdddd9hh2xn+.'_,_'.`  `.` `';?/c/L3ur>zY6bXU99OGGV}3uCI[eliL?+:.` \n" +
            "NDOXmXXHHmpV9625L5A0&&%%%%%%%%%%%%QWWq|qC}gMggDkKXQB9u<>c_'x6qdVp9}/+>+<>!<+*fhOVOVVVd9h9hw2y[,_,+,>''`   `.;sJiaAKH8R8DKkfr+39pKAUAAUPJL{||F7?!;-`` `\n" +
            "pAKXKGGVh25FTwH8WWWW%%%%%%%%%%%Q%%QWWO(wt(B0$$gHAK0WNM8SLL(EtSkdOG21jkOph44p21dXGppVd49h9kSai_,^,==,:.``` ``.-',=;>Ll2qd4UmXKAKXmmKKAP2YtxYe3t{s=-`   \n" +
            "KKXKAUV61z9mmmmADWWQ%%WQ%%%%%&&%%QQWW8Fan7$N0BBgXOgWN00NWWWW0haP6P9P}3Eqkyf5VUAUGVdh64Vdky3c_,,=,:_::-....',^_'_:__,,^_Cjua4bAAK8XGphPw][xEnoxnI/'```'\n" +
            "KKUO9k)lbVpK8R88XNWQ&%%QQQ%%%QQQQWNNMOfo}sXgBg0BA9NWNNNNNWDYAPGAdhk4pdhhhhd44VV4d9hhd6kSlc!>_:''-'::^:-.````   ``` ``.`^/sc?1wObbp69h6PwL--'':____::::\n" +
            "GO6yiuUb94VADg0Mm0WW%QQQQQQQQQ%%%%%%%QgB#$WQQWMg#D$D8DD8mHmGPe/VGOppUXm88HKUbGp4444d6Y|??**}ha|=`'__==-````  `````  `.. +zccT]OUUbOV9qkhY-.````       \n" +
            "q](jKXKmmRg00#$gBMWWNNNMg$##DDDDDDRDD$BgMNWQQQQMg$gMMB$D88U8ph2s{6PPdpGbUUbGp46wju{s??z?J/L{Y{v([xORVZ_`-.`````````   ```-v*r}kVUUOV9hPw6v.``````     \n" +
            "|SHUOKDg0g$DJ*[ADHmHHHXKAKUAAKKKXKKKKKAAXKKKAAUUAKXRRHKKKAAH0h7(C}vuoYa2]2yx{s/?LvvLT{e39y{[]x2P94GbAKR$0MgqT'`` .-''-'--'.!Tv[Yq94VppVw]6['``    ````\n" +
            "8K8$0gDu=+_^:.```tXUUAUUAUAAAAAKKKXXKKXHmXXXKUAmHmXAbUUAKKKXXSlvT]5nc+rc??z?LTTLL71j4hmX$UHypAXHHHmHm####RmmD#Dgg0gBg0RT^``'.^LuakddpVGbbV4Vc-=>:.``  \n" +
            "BNml>z>-^.`       ,OUUUUUbUUAAKKAAKAKKAKAKKXUXXXKAUbbAKKKAKKXXHyzYIs/|jCL(?*7feGw#HpSVHw9AKAAAAAUUAKKXHKHmm8mKOpq]yxaa2dObDdL--!7na]k9GUKAUUbd$KE}    \n" +
            ".=c^.`             cAUUUUKKUAAUAAKXKKAKKAKKKKKKKKAKKXXXXXXXXXmXmU2v/*Ja)ah5EHaS6e9hj9XHKXXKAUUUUAXXXXKHmmHXXKKAKKAGVkye}}{uEhOmPT,z[]hOAXKXpUx}K5bt`  \n" +
            "                  ``/OVGbUUUAAAAKAKKKKXXXXKKKXXXXXXXXXHHHHHHmmmmmHHHmmXAUAHHXmmmmXXXXKKXXHXXXXXKXXKKXXXmXXHKKUXXKKKAGbVj1(3eZxYn[[kuvnqbEjdK)YPU{tB}'.\n" +
            "                 ```.)S29OUAAAKXKAAKAKXKXXXXXHHXXKXXXXXHHHmXmmHXXHHXXHmmHmXXXXXXXXKXXXXXXXHXKKKKKXXXHKKKXHXKXAAAKAUbAUUO6Yvc!+<,--`</{[2qqejatZ5o56}--\n" +
            "                     _eGio64OGGUUUKKKKKXXHHHmHXmmXXXHHXXKHmHHXHXXKHmHXXXKHXXXXXXKXKAKKKKKKKAAUKKXXXXKAAKKXXKAAAAAAAUUUUUUGw).`     `,;*|wpKKXXKKGV99G)\n" +
            "                      _Y2J7jP4GObUAKKXXXXXHHXXHKHmHHmHXHXXHHXXKXXXXXXXXXXXXXXKKKAKKAKKKAbAUUUAKAKKKXKKKKKKKUKKAUUAAUbbUUU9-            ,ejkpUXXXKAUd4p\n" +
            "                     .{}({*zIwdpGGUKKAKKXXHXHXHXXXHmmmXXmHmHXXXXXHXXKXXHKKKKAAKAKKAAAUAAAAUAAAAAKKXXKKKKAXKUUAAAUAAAbUGGT  `           `_n]PhGAKXXAbV4\n" +
            "                  `,Y'!*ztCsLuEk9VOUUAXKXXKKXXHHmmHHHmHXmHHXHmmHHHmHXHXXXXXXKKAUUUUUAbKXKXXXXXXXXXXXXXKKKKKAAAUUUbGV4d]-``               '1yS9VGGAKUGO\n" +
            "               `eWggBB9cfo1((tZSP9pGbUUbUAUbAAAXKKXXXHmHHXXXXXXXXKKXXHXXXHmmmXXXKKXKKKKKKAKKXKXXXXXXXKKKAKKAAUUGGpV9S]<                   .7jq6pbUAAAU\n" +
            "         .`?K0g#D####8Oh}}1{C1ex26hdpGUUp9jeC(vL//!>;,,,=r*<><>+czz?v)J7|{fl[]wq69ddpUAKK8mHKAAAAAAUGGGUUUUbUbppVdkanr                      cxw9pGKXKK\n" +
            "   `JUH0NBg00BDR8mmmXhk6Ox]qEeu3z--`                                                            `.'^Lj4Od94dh4dhk2xf(_                      `!5S6dOAKK\n" +
            "'S00gB$gBBBg0gmXXKUd9Vp94q2l::'.                                                                   .``  ```?tYZeo3|vv'`                       <YS9VObU\n" +
            "DD88DHdmB##$$mpOVPy5ky}3}*'==,'.                                                                                '*J7),-:'                      +Yk6VOb\n" +
            ":/3Y]E2SdVP]u}3Fc=:.`` ``-''.`.'.                                                                               `-^_':_::'`                     z]69VG\n" +
            "__:''''''-..```````...`...c;'',:'.                                                                               -,::___::__'                   .vxPhO\n" +
            "_,,__,,^,:--......```````,--:<,```                                                                                `__::_:__,,,_-                `TfjwV\n" +
            ",,,,::::_-...````````    -v=^_-                                                                       ```        .7L!;^,,,^^^,,,,'              `J1e]h\n" +
            "_,,_'':--```   `````````:-                                                                                       >ZI13ItI{ivzc+>=,=>`            .?yyw\n";
  }

  private static String getInvertedDefaultMkAscii() {
    return "33ICftnt3n5nlu1teyY7|aK8pYxqSj5ooZ]qEYxYxYxY5YYx5ZYYZZ5YxjkH$$g0MMWQQWW0BBg0MWNBBBB$$0Q%@%B8#DmK4hVqd$Q0VhDWMH9hKg0$AkakXBUv<7#Q&%N$XKmXXO66hpm0WWQQQQQQQQQNM00gggB$g0$Rg0mq1noul1tue(v1nY225u}fny]kGmDD\n" +
            "ff{C}I1tC}n[Clya]]j3TTukGqxyyYZZ5xSkSxxY555yY5YY5YY55555o5kmBBBgNNWWM0NNM0g0WN0BBBB$B0Q%&%BR#D8XVhwYljKgKb$NN8VdAgMDKkYPmgm2Zm%%%%QMB8mmXVww9G80WWQQQQQQQQQN0ggBBBB$$8XA#Mc   'ZCsFT.   `'<Z]Zl3lZ]6GAKA\n" +
            "(|((i{C}fCnn{iton[en(zJIyy5Zoe5552qhSjZZZxZo55ZZZZ5nnneoZ5wX$#BB0M$KGK8B0gNNWNMBB$$$$0Q%@%BDDRmHpkKgV3IAM880MHpVA$g8OqYSHg8OK%%&%%%QWMMMMmhw4AD0WWWQQQQQWWWNgB$##$$D#$Rm$Qv' `=5fL/*     ` <YSjyjxx6bKXH\n" +
            "J(|Fi}}}33It}3u1331eC//{ufvieZoeo]P9ayoeYZZ55ZoeoYZenneen5PX###BNWN$8R#BNNNMWNMB$$$$$0Q%&%BR8mmX44X0NX9p8Km0MmOOK$$XGVa2ABRpOQ%&&&%QNNWWNRUOGAD0NWWQQQQWWWWNgBB$$BBRD##$RGV6y]jo}|7Ji;:''->3Y5S2Sk9OAKmm\n" +
            "F|ii{}C}31I33I}F(}}}Fzc(3}iIeneeo]PPSj55YZZ555oneoZeen[u[owX##$B0WQQWWN000gBNNg$#$$$$0Q%&Q$8mXXmGV8gWN##HA8gMHpbRB#XpV64m$8hGQ%&&&%QNNWWN$8HXmDgNWWWQQQWWWWMg$$$$$RDDD#$8AOO9w2yetIuIJvT1ywk]S4GUbOOKKXX\n" +
            "iii||{Cii{f1ne1F{}}}Lr+?fi|ZE5nnoyE22y555e[eZZZoeZZZol[[nowHDD$B0WWWMMgggMgg0MgB#$B$$0Q&&Q$R8HHXpG99RWWXk5SqVhaqbKDXUAHR##HGK%&&&&%QNNNWM#HAKH$gNNWQQWWNNNNMgBBBB$$##BMWM#DmOdhPhS]Sw]j5[oEOUXmHHApGAX#D\n" +
            "iFFi{{{}ft1[tl1[5]ae1s+>?C1Yx5enea2a5ZoeoZZneZZZeoeo5ll[neSmDR$BMWNMDHKH##$g0N0$#$$$#0Q&%W$RRmmHGGPh9qC7CL?(jhuifi[|J[kX8Dmm$%&&&&%QNNNN0#mAUXBgMWWQQWNNNWN0gBB$$$$DR$0NN0$mKAAUOO6k4d9]y]hm8RRRmbpUUKDg\n" +
            "iiiC3u5ZnYwhpAK8HUOVxfsc<sIIFI5a2ajoZZZZn[unnnen[[ee[l[[u[qmD8D$gNW0he[5PKBNNWMB$$$$DgQ&&W#RR8HOPUUS11Zo6dhw]5]n{r!|}jBQNM$RDgW%&&%%QWWN0#8XK8$0MNWWWWWNNNN0ggggBB$#DBMWNgBD$gBBDXp4pXHUA8##$B$8KAAKKmRB\n" +
            "EwqdOO4j1u]pDB$XGBB$XEfJ*+sLsia]yZn[eoeeeen[[oe[[[[l1utttlwm#RR#0NWWBDDX9pBN0gB$D#RR#gW&W0mKX8$$GPltaj]OG]Y[Fcrz(x0%%%%&&&%QM#UOKNQQQQWWNDHAAm$0MNWWWWWWNNN0BBBBBB#DRBMWNM$##$B0gDOOK##DD$B0MMg#mKKXXm8D\n" +
            "m$D8mpj3CiC[dHd35dhYiFoIT+L{v|5]Zen[neleZnu[nunonnlu[u131nkR$D#B0N00B#0NNWWmU8$#RD$$DgNDXDgDOVVpGdRXH8Ayaq]dR$K9mQ&&&&&&&&&&&%N0RApKBMWWNDKXK8$0MNWWWWWNNMN0gBBBBB$#$gWQW#R8#BB$#ggg#D888DRR#WNN$XmAKRB0\n" +
            "XMgHdZI3}|I1[ySxno5[{L)7T<>{f*{j5n[uuu[l[ul[lt[tlnllt1tu1[S8$DDB0WN0BgBBgMMDHDg#D$$RU$$DD#DDDXbGA#gRp9kAKU0W%%%Q&@&&&&&&&&&@@@%Wgg0DKDWQNRRAU8$0MNWWWWWNNMM0gBBBBB$R#0QW#DURMQQQMM00NNMNN$XGK#NNg8mmm$0W\n" +
            "xySEwe33ll33i(ItI{{F(vTLs>,*3sLu5euu[[uulttlltttl3t[tttu3lSR#RD$0WNNg$HAX#g#R0gBB$UbmBg8#BB$D88$0ggRDOB#N%QMQ&&%&&&&&&&&&&&&&&&%%QQWgRXgMNmKm8Bg0MNWWWWNWNMggBBB$$$DD#DgHGK$W&&%WD88B%&&%0mOVXRB$RRRRD$#\n" +
            "[nZZlC}3ul3fCJ|u})v((|||7z>=,_<(x[31tllllt1I111I31ttt33I1uq8#RD$0WNNgB#DD$$$0NggAap8Rgggg0$DRmmR0000N%%%&&&%&&&&&&&&&&&&&&&&&&&%QQQWNN$8RM0RmDg0Bg0NWNMM0WNgBBBBBB$$D8m8mKR0%&&&W#pp$%&&%08bUOOAXKHRDDUp\n" +
            "ii}F||C1u[}i|JL3y[iJ(}C}f|/=^<;/ou11tullulltt1311II3133I3lE8#RD$0WWNgBRRRRDRN0XawUR#R0$0MmXUmgWW%%%%%&&&&&&&&&&&&&&&&&&&&&&&&&&&%QWWWWNN$8BNMM0M00MNNN00MW0g$$BB$$$DmXKHU80Q&&&&NRbAB%%%%QgAOOGUK8RR8mUp\n" +
            "((|(7{un[u1I[nZnZP2[tu1{}Cv+^;!zttt111t1fI3II333I33II}f3IuSRD8R$0WNN0gDRDDgMMeieyAGb8NW08URBMWQ%%&&&&&&&&&%&&&&&&&&&&&&&&&&&&&&&QQQQ%WNN8O80NNN000NNNN00NWMBBBB$BBgDHAGX#N%&&&&&MmOKg%%%%%QBHpGKXXHDRHpp\n" +
            "F||{tYx]pUhqhP6day9kxZ[{({Fz<!){f3IfI313333I313If}I1333II3EX8m8D0WN0gBR8R$0miFCnwGGgNN#mVA$NNNQ%%&&&&&&&&&&&&&&&&&&&&&&&&&&&@&&&%WNgBRggRX8$MNWWNMNWWN00MWM$$$$BBgg#XUARM%&&&&&&0mpX0&&%%&%%N8KUKmXDDHVG\n" +
            "fItt5ye[Y[tu5[IIlfoPwx[I||is/L{3f}3IfI133f33III}}Cf33II}ItEAmm8DgNWM0BKUmDGJ{CIn2O$M$HmmHH0N0MQW%%&&&&&&&&&%%&&&&&&&&&&&&&&&&&&%%WMBRmRDD8#MMNQQWWWWWN0MMWM$$$BBBBgDmKA#N%%&&&&&g8Om0&&%%&%&W#XKA8RDDKpU\n" +
            "3tlnyZFF15nuyj1FioZexaZt|7vL?)If{C}II31IIff3IffII311113IIuwm#RRDB0WWNMB$BSJu|7oG0MXAOHm9O#8m$NNW%Q%%&&&&&&&%%%&&&&&@&&&&&%W%%%%QQNg$8RDg$$0MMW%QQNWWWN00MW0$$BBBBBBBRAUDN%%&&&&&BAGmN&&%%%&&QDUbKmRRDAOb\n" +
            "tuuZYlI1Z]qdXO6]u3x]Z[YuiJ)TJC[[eno55e5oeeeeeZZZeeee5xo55xh8RRRRD0WQg0NWaT{z)5ABAPGVG8GKDAmRBDBWQWWQQ%QQQQ%%%%%%&&&%%%&&%%%QWWQWNBBDDBg0QN%QQ%%%QQQQWN00NW0$$BBBBB$$RXbRM%%%&&&&BUp8W&&%%%&&WRbUKK8RRAVA\n" +
            "tn[nYj2wdXD#mm#$DDHHbqY1i(vLia9VOVppOVVpVpbGOUKbAAAUGOVOGUmRHmmR$0NQMggh(fi)CU6aVV6AmOH0mm#D8DD$A8D0B0NWQQQQQ%QQ%%WQQQ%%QWMMgg0Ng8X#$DMQ%WQQ%%%Q%%WWWMNMNNg$$$$$BBB#RKK#Q%%&&&%&$GG8W&&&%%&&W#XAXmRRDKVK\n" +
            "uluekbbX8mAO4qyZnulte]SYC(7TvyD$mXUbXKUbbUAAAKAKKAXKXXHXKKHHKmmR$0MMg#yitlCf4y5bpa8g$gMgggDR#RRR$$mB8#B0gMMQQQWWWQQWgMQQWBBgBRRRR##$BBW%%&%&&&&%&&QWWWNWWMM###$BBgB#Rmm$WW%%&&%Q$bO8N&&&%%&@0XbGXRRRDKGU\n" +
            "o[luad]1I15EkhhS25ZoeZ5eIii7, -^z(lOg$HKHm#Bg$RDD#DRRmHKKXmKXH88#gMM02F3oItO22XV4NN$WQWQgg0$#DDDBMBDDD#R#$$MMNMgBMN0gBB0WgA8B#DmHDMWNWWQ%&&&&&&&&&%QWWNWQW0#DD#$BBgBDHm$NWQQ%&&Q#Xb8M%%WQ%&Q#AVUmDRRRKGU\n" +
            "n[u5jZltlZYYol13lo5Zo[f}I{|)=       .rFpB8GI>iRWg$B$D8XKHmmAbAH8DBNWh}nYYaHUAR8RWN0Q%%QQMBBgBB$#R8R$$XG8D8DRmD888ggRRgg#XBMgDMNQNNQ%%%%%&&&&&&&&&%%&%WNWNgB##DD#$BgB8XR$NQQ%&%&%$KADN&%QQ%&%BXbHRDDDDXOm\n" +
            "u[nY5e[uuuICFFF||iCletfI1{7Jr.   `.` ``.-=<vlOB$D8DD0BB$$$#XUUAH#0NOejkEpB0XmDg%QQ%%%%%BAbmmARmm9kOB#G5AAKmROUOKRKBB#gB$BH0%QQ%%%&&&&&&&&&&&&&&&&Q%&&QMNNgB####$BgBDHAm$NQ%&&&&%$bADN&&%%%&%g8UX8RDDDXVD\n" +
            "lneZYYn[[l}C}tZZeZyyYt[nIiJv<-''',><>+!!*zLs{2]hdGXD0N0gB$DUGbUX8BXexAOXgNBggM%QQQQQWN8kdpPhOGpKntpKpd1EUGbmbV]5GX##B0g#NWW%%%&&&&&&&&&&&&&&&&&&&%%%%%NNMgB$###$BgB#XOABWQ%&&&&QgKAgQ&&%%&&%gXKH8R#DDXp8\n" +
            "1lZy]SEjYxYYxEkkqk6qE]o[e(!>*!><<!c/?v7F3CC(J|{3xu,-'>z{EOKRmXAmB$k5GAUX0D0MMNQQQQQQ0dZat{Y2jqqS((VUO5[{eSEXbE6w5VmR#g0NQ%%&%%&&&&&&&&&&&&&&&&&&&&%Q&%N000g$$D#$BgB8UpHBW%%&&%&%0KA0%&&%%&&&gXKmR$#DDXX$\n" +
            "3oYa2E]Swqqk9p9hhh96PwS]Yc=/L*!L}|}11z>-_LekqxI|Io^`      .,Lq$$RHYSR8Rg00NNNWQ%%QNg911(vitftYY(!*kUAY[C7xZ2bkkUaZkdK#gNWQQ%&&&&&&&&&&&&&&&&&&&&&&%Q&%NM00B$##$$BgB8AGmBW%&&&%&%gXK0%&&%%&&&gXXR#$$$DXX$\n" +
            "I1eZxy]2Eqh94dd9h99d6waj/^zLz?7I[>.:=''-..`:k]|(oU3^          .>Y66KBD$MNNWQ%%%WWMXEut1{J(Iluic>>=?yh6Z(zJ55e46]O6n5xSU0WQQ%%%&&&&&&&&&&&&&&&&&&&&%%%QNg0ggDDD#$$B$DHU8gQ%&&&&&&gXKg%&&%%&%%gXmDD#$BDAmg\n" +
            "iCl5Yy]ESq69444499Pwyx5(+?L*zv{ls''_<+><!!<,3Euljdv;.````..`    =k#D$DNQMWQ%%&WDphynn5qpVqan7c++<<+c+czvc<+7CJ)L(]9of13e9$MNWQ%&&&&&&&&&&&&&&&&&&&%%%QMg0gBDDD#$$##DmKRgQ&&&&&&%#U80&&&%%&&%gHmRR$BDRmR0\n" +
            "*|iC1nZ5YYx2SwSqwyYZo[{sTL?zLJ3o!rTvs/c+!rccc*z!<l61*nJ,-..-..``e8UggBWgMQ%%Q%8Y2dnfF7{IY]wwEZ|/*!<!+<>><+<!s(fto2hkkd9VPaxkmQ%&&&&&&&&&&&&%%%&&&&%%%QNM0gB$##$B$BB#mKD0%%&&&&%%$HXN&&&%%&%%g8X8DBgD8U80\n" +
            "+c7fCiI1tI1[YYxjx5t{C|zs|??TJFiLss**c*/z*?zsLTLLTvL(3vzr-..-.. !kD$$RgQQQ%%%Q$IeV6wEay[}7JCujy[|s*!r+<<<!zLTFft[55[l}TLL){5Yu5HWW%%&%%&&&&%Q&&%%&&%%%QNM0gB$##$BBB$#HKDM%&&&&&%%#XmN&&&%%&&%gKHR#BB#RA80\n" +
            "*!!|[fF||{C}1l11IJLJ(c/sz/zs)Tz**/?iisTzzL>_=uSe3nwps'..-----`.{mg$##NWQQQWWQPiIq9hbUPyuCLL/s}jois**r!!r*Tv(ifIf}Fi}}|((7}u2y}Fw#BgM%&&&&&&&&%%Q%QQQQWNg0Mg$$$$$$B#8K8$gQ%&&&&%WDbXN&&@&%%%%BAXRBBB$8GXM\n" +
            "=r+<7Zt)v7||Fi|JvvLr;*s/ccc*TL**sFFs_`-_,:` `LpY||]$('----'-.`'ug##gBgQQQQQNN17|FewHRm80pS53|(7oZJ*c!!c*?|}}{|7JL?TF35ajZ55y]e{|[6mg%&&&&&&&&%%QQQWWWNMgg0B##$$BBB$DHhnwU8gW%&%QRUmN%%&%Qm4GX88DBgBDmAmN\n" +
            "/c<ccst}TLJ))JJJTc>cssz/*c/?sc?L7T_`  .:,,-.-_xh1IyRY:----'-.`/w#$$$$0WWWQ%%4J7z?)3]wod$8Rqu1uTTn{c<<<czFuIF777(7i(({126kwww2t{(F}28W%&&&&&&&%%QQWNNWW0#RBBBRRXmD$DDmHA4hP4HMQ%08bKMQ&&0d1(CyUm#Bg0$8K8M\n" +
            "6]|,''-'=/?zzLLL+r?7FJsc//crczvJ(+!r/cr!!==;<zr<r>fdp<..----.'iKg8R000NNNW%%y+;<+rr)x]to5f?cLFLcJvc++<*JJvLFu5jdUYVBXA4w22ayn{((|C}ZM%Q%%%%QQQ%QWNNNgNg##0B]6E6wwG88#BKt?Feo[HW0HOHMWgVaIJvina4KD00$mK80\n" +
            "|i{iJ<,---^!svL/?c*/>;*/*crc*zLTTz?vv)vTz/cssLTLLT3Yqx;...---'1R#DBBNWNNWW%%|:;<++*zL)JJJzc+<>cLTz+=>!?Tz!>css{2X$D6Y6HOnfivLLTJ(}1aMW%%%%%%%%%QWNMMg00DBBB#AIL}2jT+s(Y][C*cczED8bmDGxI(T//LF3nSGDBDKORM\n" +
            "7z>*3I(+_''^L7L/>rr>*JLz/**/zz?sTvTL)C(vL/L(|iCfII}(vv'.-----,YDmB$gNQNN0gQWT_;+rrccc!//**r!+cLvLc+=;!/s/r<<!zzT|flInZY3vz/*/sv7|}uhNN%%%%%&&%%QWWWg0NM###$BgUj|cc+r+!*TC3{L/*/YAH4dx3{(TzzL(|}3YOR8KARN\n" +
            "_::,,!JI7crcrcc+=>>?ssLTLss?/LsLvJ({Cs>==vI1}FvsLTi}3[,`.----<wRHB$BW%Mg$0%Rs<!c*r!!!!!++++*/T)vz!;;+czzzc++rr!rz?s/****!++r*s)(i3ZUQW%%%%%%%%QWWNM0NNgR$##D$B$H]u|Lc<;>+cTv)/rziUVdS5tIu5ellt5]9KDRXORN\n" +
            ",___'_>/*z?/cc!^_!vvTszvJszzzsLvFfz++?)|1nnoZ555Zu}lGd>`.--'-/4#R#80QQ0B$M%X?*zLss*cr+++++rzT7L/c<>>+czzz*r++!***cccc!+!!rrc/T(ifewDWQQQQQ%%%QQNN0M0ggggB####$$BBg$63T*+>>!czL/!r75ZtF{J(|F|(I[5w9UAKU$W\n" +
            ",,,,:'^!*<=>sJ=_LTTTTvTL??z/?TiIjtsF[5aa]wGKVOwe6Z{}E8/`-----+kXA$8D0N#XDN%9czTJvszz*c+++!*ziL^!+>=<r*ss/*c+>>;;><<<<<<+!r/sL7{I154BWWQ%%%%%%%%WNg$DD$D8#D#$D#$$Bg#HXOeJz*<>+!*zz!zCFTLLsLT)JJ|ifIlyhAgW\n" +
            "_:_____!z,_^<,;L)v)vvv/ccrccsFtedGdbAXAUXDXw(^.r4aCiER7------+w$$mHM00HbRM%X/T7|(7vs*crr!r*/(J/!<^^!/sT)vs!>>>>;;;>>+!rc*?sTJFItoyGgWNQ%%%%%%%%%0XV969ppdkeyVX8##B#8UXDHyfFvc!c)(?J}7TJT+=+/J|iI1u[o2OB%\n" +
            "::'':_'^r<^=,>z///ss?*rcrccz(tkUOKUGbHbj(<-`.''=kh3JeHZ_-.---:e8AKmgR8O9HRWBJvF{{i||L//*!+*|It}?/zTv)TszLLc+++<<<<+r*/sLTTJ|iC1tnyU0NMQ%%%%%%%%Q00Dmh5lfr<>>!!!!T1a4XR$00w((JLsv|vF1{7)?<=;/({1uZ2SqdA0&\n" +
            ":::_::=vIc-'>!*sLLszcrrrr/){twUXKAUHh|-.`...-'-:1Au73Od=.---.`I$AmH8DAOdU80Q}){}{{{(T?zzr+zISd4yn[yVKO]t|cccrrcc*//??svJJ(|i{{t[yq8N0NQQ%%%%%%%N0Q0Wp1oe}vsc+!+><!rrc)5G$d|T)Lszv)fEY1f7LLJ|fexxjhOKDBW&\n" +
            "____,<)I!'_^*TLs?//c**c*zJ}[6AUpd9dJ-`.....''..'JUZ|}4X+.-.-- !P8mXRbK9ddK#Q]Fi{i{F(vvs/**c***JlI|J//?L)*<!rrc*/z?sTvv)JJ7(|F}t5wK0WBBQ%%%%%&&%N0MgV!<s}eqV9Y7*c+>><+!r*F4q})TL?v{l2]yan31[Za]]]wGD0WQ&&\n" +
            "____=*c,-'>T)/zzzz/**?L)F3YOXOa]91_-.....-'-..':vGjiiER/.-':_.'eR8AUKmd9GKXNRuFFFiiF(TLTsz//**+<>;>><+rr!<!c*//zsLvvJJ777(||Ff[yhb0MgNQQ%%%%&&%WM0MDu|{7sc(]XRGx{7?++!!?v(|C|LssvC[kaZnn[ul[Yqdd4URNQ%&&\n" +
            "_:__,'-';zzrc*/zz?ssv)J}Z6AUVdai,.``.....----.''!hEC|xR)-.-_:--,yB0$#DGG#$Ug#5fi|(|F|Jv)vTsz//<==;;<+!rrr!c/zzssT)J)vJJ)v7|{tuZX0M0gBMWQQ%%%&%%QWNWQ#KObO9En}eA#DU95}src*/LJJ7vLv{e9Z55Ye[unZjq4GKRNQQQ&\n" +
            "_:^:-^zz*!!rr*c*/?L)|}1o6AUKVi: ``````````` ``.`:ydti58i-.-'_'-'=aRmB$mm8$BBNP1CFFFF|7777sJ1jax}?JFvzc!rc***zsLvv)JJJJJ(((CIuZ6WWNM0gg0NW%%%&QQQ%&%QNBUbAXKUOw[2H#Hp[Fv/r*v77vLssLF6]xaq2YZZjw4GbKDNQ%&&\n" +
            ":''+J*!<ccrr!rc/zsL7i3xPX8hs- ``````    .```...--CpZF[Xt_':_^+!;,;JG%08Um#MgWBe{iiF|77(|ix9pU9qhp49VOxv*??sszsvJ(7JJJJ7|FC3el20W0g##B00NQ%%%&%QQ%MAXmUAK9ww6OUU9qdRm4u{JLzsLJv/c!/LnAbVd9wa]hOAmDgWQQ%&Q\n" +
            "'_)?c!r*c!!!!?vv7(i3npdI*^   ````````   `````.-.-?bj{tG5_'_,_=?(*c=`^Ll2RgKm0Wp}({|JvLviSbm8D8mDRmRRmXUx(TTTLTvvv)J7(7((Cf3lYDMggR$0DMMN%%%%%%QW#dVhknv**r++<>+<*)lwXD4C7Tz*/z///zzIdVpwP6PP9pK8#MQQ%%&Q\n" +
            "LvLz*c**/?LJ|{I[oywdJ,-.--   ``````````` `````---*42|Ipy,:,,_:rJLc>-.-_*nb8mmUnof{C7vv)TJaAk3}[o2wx2E48mp3)TLTTTTTT)J7(FCI1eOMg$$ggBWNWQ%&&&%%QQ%Qgw1I|7)L?*++*/zz//TI)/)v*+>cc/TJ7f2axZnZ]SwhVGm0Q%%&&&\n" +
            "T/**c*zLsT7(fu5dp[cr+:..--` ``````````````````--->6q{IO2^',=^,^*/*>'-'._+TjbO45Iy}i|JTssfa51iLsv()vvTCxjJ!?LTLTvJ)J7777{}Iod0g#gg$$$g0NQQ%&%%%%QQWNNg$$DRA65Ii7)vs*!c****c!+!r**/7II[aZt[ojaySdA$gQ%%&&%\n" +
            "**cc*/zzT)7i[6Ou+++!/='---.   ```````...---...---=]6f3Ga^'_==^,=/?<'-':_,,'.-:,_(x1f77Jv7[Ye[enItlnZw]lLc+!/sT)J(77J7|iftjp#Bg0gBBB$0NWWQQ%%%QWggB#B00B#DR8$$mPYu7s?z?L?*ccccr*sTCI}lSyYYxSSjwOA$W%%%&&%\n" +
            "*ccc**/sv(uSUY*+!+!!*+'--.` `...````       ``.---,n413dS=-:_,^,_rT+---:__'------_)ifF|||77YwalC{i1[EnJzz/z?Tv)J(|)))JF}l]UggggB$B0M0MMMWWQQWWNggB00M0M00gBBBB0HjlYo3vs///*!+czsv(|i}35Zt}e5nuoywUBWQQQWM\n" +
            "cc**/?Tv{xbkJ/*c!!cc*c_'-.` ``.......````....----_{2(i5y!_,,,,^=<s+.-':_'---..`+)vJT(Fi{|J7(tj]]ye{JTLsssLvJ7(|()J77F}[kRgMNWM0$B00MMgg0WQQ%%&%%%QQQQQQQQWWNNNWU5t[qq3v?zz/**sJJ7(||}jkExYyEk94GX#g00000\n" +
            "**/?LJ|tdq|zvv?c+<!++*,--.` ````.```````.....-'''+a]ox9dc_^,,_,=/7r--'''-'''-^|l/J}?/v||iJTTJ777((7)vvvvvvvJ7((7J7|i}o4HmR$M0NNWWNMM0gBMWWQ%%%%%&%%%%%%%%%%%Q%%RUUAXGw17L?z?T7(77(|iCwWWWNNMMg0000MMM00M\n" +
            "*zzT(fyVnzL/zs/*r+>,,*='--` `....`.```..````.''''rG025OG*',^^_-..-..:,;=^=>+r<Fvz(]L/sTTJvL/ccc*?sLLTvTv)))J77vv)|ilSGbKUqAW%Q%%W00g0g$gNQ%QQQQ%%%%%%%QQ%%%%%%&NUKHmHde{)))v)JJJ({|F{lgQ%&&%%%%%%&&&&&&&\n" +
            "?sJitEd}zzJ7vLLc>,__'!>'--``...........````````` `'^,=;^-....'_,=>==>+<>><++r>zLFIhPv?sLLLs/cr+++!c//?ssTv)))v)J|IxdbGUKKhu)12bW%NM0g$$BW%%Q%%%&&%%%%%%%%%%QQQ%%8GXKXUj|sz/zzsTv7F{F(CKQQ%%QQ%%&&&&&&&&&\n" +
            "T7{t6]L/Ts*!+<>=_:::,+=--.``....-----''':_,,,_:::'---....-':,=;>>>=^,,===>++<<^T215KOfTT)T?/cc!!!rcc**?zT7(|((Ct2pAUKAGK8pyly3?<+iyV0NggWQQ%%%%%%%%%%%%%%%%&%%%%QApAKX2fvs/*/?Lv77{C}ixW%%%QQWQ%%%&&&&&&\n" +
            "i}Z6tz!c*!+!<^_:_^<,,^^'----''':_^=;;><>>>>>>>>>;^^,_:'---....-'_^,_'',^=;<++s^rL7|[GV3JFFJvLs??zzz?sLvJ|{3[xwVbGbbUb4VHOqw3qp9wYn|/>c7]0QQNQ%%%%QQQ%%%%%%%%%%%%%QXpKHp5i)?/zsLT7(|iCi1KQQ%%%%%%%%&&&&&&\n" +
            "I]or+!c*r>=^_:__'__::__,^^^^=><++r!rccrrrrrrcccccrr!r<<<>===;;==_:_:'=><+rrrr!v71qeS5]bw[1|F|7)vvv)J7||uYSkpGOGOVGG4P9Op9E2jxEqkqw2yZ17+^!FwgQ%&NNWWWQQQQQQ%QQQQQQQ$GKXkn}7TLLTT)J7|i}C5gQ%%%%%&%&&&%&&&\n" +
            "6e//zss+;,___=:zdmO1*=!r!rrrrc**/**z//*****c***c*****!c*cc*//zz//*cr<>!r**c*/*c!:rhd6]kPVaZe[lIIIf3tu2OUAbGpVVppp6aoa3Lxqjt3uI||7)Ls?//*>=,_'-/1Vg%%Q@%WWNNNWNNNWWQNHppVYe3FJJvTTvsJ{I{(Yg%Q%%%%&&&%Q%%&\n" +
            "ic!cz+<^,_::>(h#mDMWN67r////////z**/zz//***/*ccccccc*c*cccccc/z*c//L//zsz/**c*cr)7!Ttx][hAKUppVVp44VGOOVVVVOpVphnCvv7Fu)/;cCJ*<++!rccr!!+>;^,:---'-'^>>+c1UBNWWNWWWW%WKG6x[1FJ)TTv77777ivj0QQ%QQ%%%WQ%%&\n" +
            "+cc+=__,>3X#8BgB0Q%%QQg3c/z///zz////////*****crcccccr!!cccc*/*crr!rc/?zzz/***ccrc|[aVUnunuVmmHXKbGUUGGpVpOOV9En3)TTrc;T>!1{s/crr!!!!rc<=;;;;<+!!>;>;,____,^__=}KBQQ%NNM#O]l1iJJ)TvTszzLv)vyMWBR$MWQ%%%&&\n" +
            "c+=_'=J6XGpX0W%%%&&&&&&Ql/?zzzzzz*******ccc**cr**c*c*cczz****!!!rcccss?z///z//*cr!*i}3PHp533jbXHHXUbGUpbOdx3f}(Tsrs{s=v3i?//**ccccc**c!+>==^=>+rr**?zLvTLsLvTzr>=Tk0QQ%NAd]e1})vTL?/**cr*L?7{/-_SQ%%%%&&\n" +
            "^__zjmKKRNNBQQ&&&&&&&&%&BF/zzzz/z?zzz///***///*/**/**//**c*/ccrc*///z?z/********ccc/tV6}2GUA6uekOhhXKdP2ZsJ|;+zTCu!sC(?**///////z///***rrc*!!!+<!r?TT(}31lu131F)s/r>J6gQN$Vq5ufIi(Lsz**//zs?J!'{f{R%&&&&\n" +
            "!uXB#$gNQ%&&&&&&&&&&&&&&%XT*z//zz//z/z/*//*****cc//****//z/********/**ccc*crrcccccccsCPK8E3YOwSb5FCtE!!7<'*z/1},/}Cvrcc**//?zzzz//*crc***ccrrc*/***c/zLv7C[Y5n[u3C|Jsr+tOQRHUV]ZC(T?L/*cc*vs^Jbx=I8Q&&&&\n" +
            "Q0HHBW%%&&&&&&&&&&&&&&%&&B(*zzzz//c*z///z/*/*c*c*********************ccccccccccrr!cr!*7YVAKKdtYO17iefcz[}73n7)3tJ*!c*cc**/zzzzz/*cccccccr!!!rcccc******/sT7{tojSSwE5f|v*!TjAgmhe3FvL/cc*rssJuS{!n3cG%%%&\n" +
            "&&&%Qg%&&&&&&&&&&&&&&&%&%W4sz?zz?z//z//z/**********ccc**cc***ccccrrc*crc**rccccr!++!!++cTFo266k9efn]oeZF77*c*c!!+!!!!!c******//*****cccccccccc*rcccc**cc*/zsvCZSVp6you3|)L*cvkm9Yf|vsz/sJZPF//2Vi<jChN%%\n" +
            "&&&&&&&&&&&&&&&&&&&&&&&&%%NyLTL?zz/zz//*//**/****ccccccc**c*ccccccrcccccr!!rrrrr!!!!!!!rrr!!!rc/zz!r!*+!!!!ccccccc*cccrrccrcccc*ccc**cccr!rccrr/*/*rccc***???TFna6kjZeuu[555YtvhVZu(z|lI)zequ}zJqpT>S0NW\n" +
            "&&&&&&&&&&&&&&&&&&@&&&&%%%%${iJTLsz/z/**/*cc*******ccc*ccccrrrccccccccccrr!!!!rrr!!!!!!!!!!!!rrrr!rrccr!rrrrccccccccrccccrrc*rccc*ccc*ccrrrccrccc*c***cc*///zsT(3xyxwdpVVGGAXD2wqe1Ct|7tt})eeZI7siEHMNQN\n" +
            "&&&&&&&&&&&&&&&&&&&&&&%%%%QNdi[{7Ts/////*cc*//*c***c*rcccrrrrrcccccccccrrrrrr!rr!!!rrcrr!rcc!++!!+!cccrrrrcrrc*ccrrrccccrc****c**ccccrcccccrrc**c/**/*czz?z/zzzL|uSbXHm8R$NWWQWXA6]oIf(IZtnFy5n[ee17SMWQ\n" +
            "&&&&&&&&&&&&&&&&&&&&@@&&%%%%R(Cq[F7Tszzzz//*/**c*****ccrr!!rrrrrccccccrrrrrrrr!!ccrrr+!r!rccccr!!!rrrcrrrrrcccccrrrrcc*cc*/***c*r++rrccccrc*cccc/***/////////zzsT(nhUmD0Q%%%%&&WVUEl}(Ft5l7vc*/s)(F|zl0W\n" +
            "&&&&&&&&&&&&&&&&&&&&&&&%&&&&0q*7k2I7)Lss?zz//cc*****ccc!!!!!rrrr!ccrrrrrcc*c!!r!rccrrc*cr!!ccccccrccccrccc*cc**/****c*****//z*c*ccccc******cccc*//z///*//zsz/z/zz?)34N%&&&&&&&&Qg$8K63J?cccccc**sTv77z|R\n" +
            "&&&&&&&&&&&&&&&&&&&&@@&&&&&&QRIL3OyI7vTs/?z//******ccr!!!!!!r!rccrc***r!rrrccrrrrrrcccccr!!rccrrcrc!*cc*c**/****c********/**z/c**ccccrrrrrcrcc*//**/zzzz/zLs/zz/z/s]N%&&&&&&&&&&%&%Nkl1|)T?/**cc/?Lv)Jz)\n" +
            "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&Wd}I5Xwl{)vssszz/*c*ccccrrrr!rcrrr*!+!r!!r!rrrrcrrrrr**cccccrrrrrrcccrrccc*******/******zz/zzzz/*/cc*ccccc*c*****/z**/zzzz//zzz?zzzzTwQ&&&&&&&&&&&&&&&%Bal1iv?*ccrc**zz))vT\n" +
            "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&R5eYeVXw[|7TTz///***cccccrrrrrrrrrrr!!+++!!rcr!r!rcr!ccrrrccccccccc**ccccccc****///*/zzz?/zzz*////*crccccccccccc******//////szz???/|X%%%&&&&&&&&&&&&&&&QHZf}|vs?*c***/?TTJ)\n" +
            "&&&&&&&&&&&&&&&&&&&&&&&&&&&&WdxqhS6bUk1iJTL?sz******cccrrrccrrrrcrr!!!!!rrr!!!!rcccrrcrrrc**crrr******//*/****////z/////z//////**ccccc****/*c**z//**/z/*///sz??7VQ&%%&&&&&&&&&&&&&&&%NVnIi|v?zc*cc*/?T)v\n" +
            "&&&&&&&&&&&&&&&&&&&&&&&&&&&%RhVAG46GG9a1F(vs?Lsz/**c*crcc*c!!rcc!+r!!!!!!rr!!!!rcc!!!rr*cccccccr***cc**/c****///**/?///****/**cccrccrcc**/*****/***//zzzzsTTLT(VN%%&&&&&&&&&&&&&&&&&&&gk[fi7L?/*//zz/zvJ\n" +
            "&&&&&&&&&&&&&&&&&&&&&&&%%WaYMRAKbkokGO6Zfi(JvL?zz/cc*cccc**cr!rr!!!rr!!!!r!!rrrc!!!!!!!!!!rrrrrccccccc**/zzz//z/*zz*c*crccccccccccccrrrc*******/*/////??sT))J|9%%%%&&&&&&&&&&&&&&&&&&&&$at3i7)sss/**z?sL\n" +
            "&&&&&&&&&&&&&&&&&&&&&&%A2z::vbU9wnok64hjt}F7vTL??z/z//***/*ccccrccrrrr!!!!r!rc!rrrrrc!rrr!!!++!rrrrrrrccc*****//***ccrrcccccccccccccrrrc***/**//////z/?sLTJ((5W%%&&&&&&&&&&&&&&&&&&&&&&%Xx1}|JLs?z**//zs\n" +
            "&&&&&&&&&&&&&&&&&&&%%w!-_,,^,*6mEZZ]6hkxn}{|JTL??zz??zz/z?z//*/c**crcc!!+rrrccccrrcccc**ccccrcccrr!!!!rcccc**c*c***ccc****ccccccccccccc*c*/***///z/?ssLTvJ|CIU%&&&&&&&&&&&&&&&&&&&&&&&&%Wb5I{FJs?zz////z\n" +
            "&&&&&&&&&&&&&&&&&KZIr,,==^=;>>s]daxjP6Pj5l}F|JTL?ssz?ss/zzz/***c*cr!cr<+!!<++!<<!!!!++!!!!!++!rcrrr**rrc*cccrrrrccccccccrcc**c*******c//**////zzzTsssvv)7if1]W&&&&&&&&&&&&&&&&&&&&&&&&&&&W9e3i7Jvs/*ccc*\n" +
            "&&&&&&&&&&&&W&0E*:_^=;>;===;+zvFS6y]qqwyZut}|(7)Tszz/LJ|loyqhdpOAbH8DDBgggBDHAmDDRRD8XAGUbpVdddh6kSEjZnIC{F|7JJJLzz/*c!+!!!*/////**/z?ss?z/zzz??zzsTTv)7i33yH%&&&&&&&&&&&&&&&&&&&&&&&&&&&&0ql}(7Tsz*c***\n" +
            "&&&&&&&&@NSL_>,''_^=;;=;>>>!/7CC}2]55SaxxetIF(}yU8$N%&&&&&&&&&&&&&&%%%%%%%%%&%%&&%%%%%%&&&&&&&&&&&&&@@@&&&&&&@&&&%%&%QQMBDRmG95{T/**/???sLLLLTTTTTTTv)({Iuxqg%&&&&&&&&&&&&&&&&&&&&&&&&&&&&&$]I{i(vLs****\n" +
            "&&Q%%H|/r=`:^^____>><+!!!!!z({|7LfeIi{uZxakHNWQ%&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@&&&&&&&&&&&&&&&%%QQgBHqf)LTJJ))J7JT7(|}Iu56V0%&&&&&&&&&&&&&&&&&&&&&&&&&&&&&%m53C|7vL/**c\n" +
            "&&Q4<.^^,_,=!/;,:,>++!!cc*s)7TLv/LliiuqSAWQ%%%%%%%%%%&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&%W0W%&&%Q0U2tFi{}}i}3ueYSdUM%&&&&&&&&&&&&&&&&&&&&&&&&&&&&&%Wd[1{(JT?//*\n" +
            "NZc'__^==,,^^^,,:_>rccr/zvJJvTT()TtfxBQ0MQ%&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&%Q%%%%%%%%%g4ylneoex2P94OM%&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&gal}(7vszz/\n" +
            ":_,;;=>+/>,==^^^^;!c/ssT)||Ts|C{F|CVBW%%%%&%&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&%%&%0OpbKAKVpkBWMQ%&&&&&&&&&&&&&&&&&&&&&&&&&&&&&A]u}FJT?s*\n" +
            ">><++><zJ*==;;===</vLT(it[I71Sa]S9$M$#$gNQ&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&%%QRUdh4pgWNMN%&&&&&&&&&&&&&&&&&&&&&&&&&&&&NG[}F(vsLz\n" +
            "cr!!!rr*L?!cr!r/zzL7)i3[jS5xG$0NWWNMMgMQ%%Q%&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&%$bbGbHM0N0MQ&&&&&&&&&&&&&&&&&&&&&&&&&&&&&BSti|7L?z\n" +
            "MmPy[1f}fIJ)T({txSa2PAR$MWQ%%%%%%QMNNW%%QNgQ&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&QMBg0M000M0NQ%&&&&&&&&&&&&&&&&&&&&&&&&&&&%UnC|7vLs\n" +
            "000MNWWWWWMNWQQQQQQQWWWWQQQWQQQQQWNg$gg0M0MQQ&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&QN00000MMM0MNQ&&&&&&&&&&&&&&&&&&&&&&&&&&%Mql{7vvs\n" +
            "g00MMNNNNNNNWQQQ%%%%%%%%QQQQQQQQQQ0XDNWNB0MMQ%&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&%NgNMM0000M000gM%&&&&&&&&&&&&&&&&&&&&&&&&&Q823||)s\n" +
            "ggg00000000MNWWQQQQQQQQQQQQQQQQ%WN#80NMgB00Q%&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&%N0000000000gg00N%&&&&&&&&&&&&&&&&&&&&&&&%8k51CJL\n" +
            "gggg0ggggBBgMNWWQQQQQQQQQQ%QQ%%%%ggWWMBRg%&Q%%&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&%%Mg00000000ggggg0N%&&&&&&&&&&&&&&&&&&&&&QX9]l}(T\n" +
            "00MMMMMMM00gNWQQQQQQQ%%%%%%%%%&%Q$NN$$$B0&%WQ%&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&%%&&&&&&&&&&&&&&&&&&&%%g$M0000gggggBBBBBgW&&&&&&&&&&&&&&&&&&&&%bw5[fFT\n" +
            "ggBBg00MMM0MWQQQQQ%%%%%%%%%%%&&&%Q#O$B$0W@&&&&&&&&&&&&&&&&&&&&&&&&&&&@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&%&&%%%%%%&&&&&&&&&%N9dUmD$BgBBBBBBBBBBBgN%&&&&&&&&&&&&&&&&&&%mEyZti7\n" +
            "0000M00M0MMNWQQQQ%%%%%%%%%%%%%%%QggW%%%&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&%%&&&&&&&&&&&Q85ES6hd4pX8DD$$BBBgBBBg%%&&&&&&&&&&&&&&&&&Qh5el}|\n" +
            "0ggg0NNNMNWW%%%%%%&&%%%%%%%%%%%%00%&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&WAe]aa]2ya2wqkVUKK8R#$BBDRQ&&&&&&&&&&&&&&&&QN9t1f{\n" +
            "0gB$Bggg0MNW%%%%%%&&%%%%%%%%%%%QW%&&&%%QQ%&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&QQ&&&&&&&&&&&&&&QaYyEy5YyjYYZ5ja]]YkdUUKm8Q&&&&&&&&&&&&&&&%%QUx[}\n";
  }

  private static String getAllCyanImage() {
    return "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" +
            "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n";
  }

  private static String getNoWidthFooter() {
    return " ________ ___       ________  _____ ______   _______   ________\n" +
            "|\\  _____\\\\  \\     |\\   __  \\|\\   _ \\  _   \\|\\  ___ \\ |\\   ____\\\n" +
            "\\ \\  \\__/\\ \\  \\    \\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|\\ \\  \\___|_\n" +
            " \\ \\   __\\\\ \\  \\    \\ \\   __  \\ \\  \\\\|__| \\  \\ \\  \\_|/_\\ \\_____  \\\n" +
            "  \\ \\  \\_| \\ \\  \\____\\ \\  \\ \\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\|____|\\  \\\n" +
            "   \\ \\__\\   \\ \\_______\\ \\__\\ \\__\\ \\__\\    \\ \\__\\ \\_______\\____\\_\\  \\\n" +
            "    \\|__|    \\|_______|\\|__|\\|__|\\|__|     \\|__|\\|_______|\\_________\\\n" +
            "                                                         \\|_________|\n";
  }

  private static String get500Footer() {
    return "                                                                                                                                                                                                                        ________ ___       ________  _____ ______   _______   ________\n" +
            "                                                                                                                                                                                                                       |\\  _____\\\\  \\     |\\   __  \\|\\   _ \\  _   \\|\\  ___ \\ |\\   ____\\\n" +
            "                                                                                                                                                                                                                       \\ \\  \\__/\\ \\  \\    \\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|\\ \\  \\___|_\n" +
            "                                                                                                                                                                                                                        \\ \\   __\\\\ \\  \\    \\ \\   __  \\ \\  \\\\|__| \\  \\ \\  \\_|/_\\ \\_____  \\\n" +
            "                                                                                                                                                                                                                         \\ \\  \\_| \\ \\  \\____\\ \\  \\ \\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\|____|\\  \\\n" +
            "                                                                                                                                                                                                                          \\ \\__\\   \\ \\_______\\ \\__\\ \\__\\ \\__\\    \\ \\__\\ \\_______\\____\\_\\  \\\n" +
            "                                                                                                                                                                                                                           \\|__|    \\|_______|\\|__|\\|__|\\|__|     \\|__|\\|_______|\\_________\\\n" +
            "                                                                                                                                                                                                                                                                                \\|_________|\n";
  }

}
