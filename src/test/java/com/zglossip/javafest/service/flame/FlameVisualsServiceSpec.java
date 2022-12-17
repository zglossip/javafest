package com.zglossip.javafest.service.flame;

import com.zglossip.javafest.base.TestBase;
import com.zglossip.javafest.domain.AsciiImage;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

public class FlameVisualsServiceSpec extends TestBase {

  //TODO Clean up test data (it's currently faulty)

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
    assert result.getWidth() == flameVisualsService.DEFAULT_SIZE;
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
    assert result.getWidth() == flameVisualsService.DEFAULT_SIZE;
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
    assert result.getWidth() == flameVisualsService.DEFAULT_SIZE;
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
    assert result.getWidth() == flameVisualsService.DEFAULT_SIZE;
  }

  @Test
  public void testPrintPictureWithFunction() {
    //Given test data
    final Integer width = null;
    final Integer height = null;

    final Function<Color, Color> colorFunc = color -> Color.CYAN;

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
            "|JI3[}1It[onjyya]]5yjxYEa2aC)J)LvCC}C|(s))/vTz?scccc//zzzz/zTc_+=csTvL?z/r!+__,,!/rzz?sLTJ(7{qSSESxn5}ZC((}o51C(TTJi(1onC|(({fvf[{JIZ[ut1I}{}IIi{Iu5ttI3|}{ifCiFi33C{K%&%%%%QQQNWWQWQWWNNNNNNMNMM0MM00Bg\n" +
            "F)I1uC3I1toxZYYxYj2]ZYYjxxy3T77Tv}Cff||zsT)sLz*L*!cc*zz?/z*zvc^<>^+c*r*c!+<:_:,++<!*//?sLv)J7{t2wS5e5Ce{((}o5t{|vTJ{|1enC((|i}v3lFvIZnut33}{f3}iCIn5ulfI(}{iICiiF31{iQ&%%%&%QQWWWWQQQQWWNNNNNMNM0MM000gg\n" +
            "|)It[Cf3unxjjyy]]]22ayyxyYy1J7Jvv}C}}iJv/sT*sz*s*+!*/zzzz/*?Lc;>;<>;^+c!='::_,=<!!c//z/LLLTJ7(|F5SZn5{oi77}o5t{(LT7{F1oZ1iFii}L1liJlZ[ut3CC{3I}iC1e5llfI(fiFICFii3I{{Q%&%%%%QWWWWWQQWWWWNWNNNMMMMM00MM0g\n" +
            "|7It[C}I1eYxjee5a222aYxYxjYI7J7TvCCf{|7J//s/c*c?*rc/*/zz/crsL*<>!+<+>^___:__^^>>!!rc/*???LTTvvJ7F3ee5Fe|(7}e5t{(TvJCleZYn{}}uti[tFJuZnu1}C}{If}i}toZulf3(}{F3{iFi3I{nQ&&&%%QQQWWWWQQWWWNNNNNNNMM0M000g0g\n" +
            "iJIt[}}31oYY5[eo5]2]]YxYxaj1J7vTTCCCF(vv?c*?!!*/r!c*zz?/c**T?*+=>+>+<>>^^^,^^==>+++rc*zz?ss?sLTTJ7feeFe((7CoYti|FCC}1l[u}1Iuo1fI3i(uZ[t3}}C{ff}FCto5uuf37}iFfCiiit}iD%%%%%%QQWNWWWQQWWWNNNNNNMMM0MM00ggg\n" +
            "{)3unICt1o5YjZZ5x]]]]xjxyaatJ(vLT{fC{|Jvv<c?!!z/rrcz/zz**//Ts/+;!!+<<<<=^^^==;<<+!rc*/z?sL?z?LTTLvJ[oCe777}e53{u1IC{J/)i)fft}u}Cf{fun[uICC}Cf}}iflZ5ul}I|f{FfCiiitf{Q%%%%%%QQWNWWWQWWWWNNNNNNNMMMMM0MB0B\n" +
            "{)Iun1Cltun5Zo55oaayYa2xa]ytvJvLTCII{i|vLc+r+!?/cc*z?s/**/zLs/r<<+!+!>;=;;^=;><!!!ccc*zz////?LLL?sT([}e77JCe53IC{LL*z*+*LLTFttiI|7i{fI1I33C}fICiflZ5utC3|}{iI{i|ilCCQ%%%%%QQQNNWQWQWWWWNNNNNNNMM0M000Bg$\n" +
            "{)Iln3fftn55xZZo5aa]ayZy2Eauv7vLvIic;^;c/?;<*;sc!cczLLzc*z/??/r<<!rr!<+<<<<+<+!!!rrc*****c/zLTs*rzLv{Ce77J}ee3{T?<>;<cr+c!sFLL|{C{J|Ci}f3}iC}}{i}uYZ[lC3(}{iI{iFilCj%&%%QQQQWNNWNNWWWWNNNNNNMM0000000B0B\n" +
            "i)Iun}Cf1u5xx55ZY]]2axZYYy][J)vLJi*!;=^=c/r>!;zc!c/zL?//*?zs?/c+<+c*cc!!+<+!+!ccrccrc**cc*zsvL/;!ssL)}eJ7)Ce1iJz=,>>/zccc*Lr/ssLLJ(ii{C{{}C}fI}if[xZuu}I(}{iI{ii{t}D%Q%%QQQWN0WNNWWWWWNNNMMNM00000000BgB\n" +
            "i)fu[3CI1eeYxyjyy]2axY5xy]]lJTvTivzcc*r>=+*^<r*rr*z??/*c?zs/**r!+!!*cr!!!!!!r!!rcrcc**c*//?LLz></z/zTFeJJvCuii?;,_/v|vzsLLszz!)7s)7J7i{i|(i}33C{I[YZulCI|}i{fiiF{tCQ%QQQQQQWNMWNWNNWWNMNMMMMM0000M00gBg$\n" +
            "iJ}lnI3I1eee5yyaayYxaj5YxjZ1J)JF}{7L??*c<>!c;r/!r*?sz/**/zs//*c!r!!ccccccc!rrc!c!c*c/*c*zzsT?c^!*/c/zTuJvv{IiT*=,Iuu1C{31}()szc+!sLiv|{{isT}IIi{InxZulCI(}i{Ciiiit}QQQQQQQQW0MWNNNWWNNMMM00MM0g000B0ggg$\n" +
            "|)f[nf1t1en5oxxjxyaxaYxyx2x[({CC{srzTT/r!;++;r*rr/szz///z?zz/c!+ccrrcrcc!crc*crccc*****/z?LL*==c/c!z/*7J))CCFv*_[xjyY5yjjelC7L*+++)7(7{i(vsvf}FC3exeulC}|}i{Cii|{t]QQQQQQQWN0NNMNNWWNMMMM00M00gB00B0BBB$\n" +
            "iJIunt11tl[e55oY5jjoxxya]22[iF|v*!<!TvL?!>=+;cc!*zL?///*zzzz*c+<rr++rrcccccc****/c/*c**/zssL!,<**!!zr!/J)J3FJ(,lxaEEES2]ajYot|Tc<+;J7((i(vTTJ{|C1oxo[tC}|Ci{}ii|C3NQQQQQQNWM0NMMMNWNMM00M00g0Bgggg$0#B$$\n" +
            "FJI[[13tlu[[oojj5Yx5Yxyy2E2eJ|zr+Tvc+r+=<+>=>*c!/s?z/z////z/*!<<rcrccc*rrrcc**cc**/****/zssz^_!r!<c/r<+s((}7v)*Zy]]]E]]aajYZet}Jr>^<s(|i)vLvTii}tZxe[uCC|Ci{CiFF}3WQQQWQWWN00NMMNMNNN0000g0BgBBBgB$g#B$$\n" +
            "F)}[nl3uu1tnenZxeox5Zj]]y]2[|c/{{((7s+_r>=^^,cc*?szz/z///z/**!<+!ccccrc*cc*************/?szc';rr<+cz+>+zJ|{J)>uYjEaa]]jjY5Yon[1ITr^,z{Civs/vsT{Cl5ye[t}CFCFCCi||}ZQQQQWWNWM00NMMMMMM00gg0BggBBBBgB$gD$#$\n" +
            "FJf[nu1leuenneoYZZYxYa]2]2]e?IC|(()7)7J((!,,^cc/sz/c*/z/z//cr<++rc*crcr**c*****/z/*c**/???*<'+r+<!*!!>++Ti{JL+ox]]2a]22]yxZZnutI|/+^+7f|7L?zLLTfuZyonl}CiCF}Ci||}KQWQQWWNN0g0NMM0MM000gggggBB$$$gB$B#$##\n" +
            "|Jf[[utl1uunne5jZ5Y55ya]2]yI(7(J)TLs)7(JTvs,=rczs?*z//*cc**/r!++rcrcrcc**/*/**c///***/*zzz*__++>;c*rc*^<J3{)<{Y]]axaE2]]yx5oenli7zr++JFJFT?T/sLCu5ae[t}C{{|CCF||INWWWWWNNM0B00000M00ggggBgBBB$$$B$$$D#D#\n" +
            "(J}[nutun[un[n5xZZ55Y]a]]]n*s/!>==^;+>,;z7)z=!*zzz//**/*cc*rr+++r!**ccc****//*/zz/**/z??z*r'=!>=+rc+**;r/{iv^[|JCenyx5xxx5Yee[tC7?c+<FC)7)czTsTT[Yaent}iC{|}{F||3NWWWNWMN0g$000000g0BBBg$BBB$$##B#$$D#DD\n" +
            "77}[nutu[uto[exyYZ555]]]]]{c^^+c<F3tnn[li^!)=!*/*//*/*cc*crc!<++!r*cc*ccc/z/////*F(TLszzz*!-;<=>!c!+c/>c(C|z=e3T)}oe[C)J|{}nn11}7z!>=|CJ7v+zssLT3Y]e[t}iC{|}{F|7kWWNNNNMM0g$0000g0ggBBBg$B$B#$R$$D$$D#RD\n" +
            "((C[[ut[tnoZeZ5xxZ5aya]]EYT*L?>?}tunnnn[1}7,/>c***cccr!c!r*!+!+!!cccrcrc//z///z*zi|/?T)||z<'><>+rr<cr*;c{i|ci)(LTIo51i(LT3e1ut1t(*+>^J(|F)L<*z?L{Y]o[1f{C{F}{F|(MWNNMMM000$ggg00g0BggBBB$B#BD$D$#R##R#8R\n" +
            "((}[nuttuu[eZjjyaxx5ZYyaSl(?*'*{f1lu[nnn[t}(_<!rcc!!cc!!++!++++c*c*cc*c//zz/z///)vv(7vcsFJ>:>>>+c>>^>+<*ii7zv?*z{IYYI[{TvnJ1tC31f?>>=)((|vsr<c?LvZ2o[1fi}i|}{|||0M0MMM00gB$gggggggBBB$$B$$D$##DD#R#RD#8R\n" +
            "7(}[nu1t[[[unejx55ZZyyay]CLz<,7{}f3tuneeenuI)^>+!!!+!!!+!+!!++!!cc**ccc**/*z/zz*J/J)*TvLJT-:>=<+!=^_>>>ci(i?Fr|{io2Z[f(L+c|(f1III7;>>T(7|L?<>+*/vn]n[1fi}{|}{(|uMMMM0M00gB#BBggBg$B$B$$$$$D$RDDD#RD8RRm8\n" +
            "7|}nnut[1ttu[Zjyj5ZeoYaaa|*s=r|{{CCf3tnneooonnf!>><+<<<!<<+!+!+!ccccrc/*//zzz//?)JLTLLvL!?-,<>++,,_<!=</Jv|?t[[n2aZot5euF(s*)I}}3C!;=L|JJ/z*=^>zT{aen1fF}i|}i|($M0M00M00BD$BBBBBB$$#$$$D#$D$DRRRDRR888mm\n" +
            "(|fn[l331ntu[exj5oooyxjx]{!/->JFFFiFC}3uunoZen[t}!;><<<<++!!++r!ccc*ccc**/z??/zzz??vcsTs/c-^;+=;,:>!<=+/JJFsj2]SSn3tojxxetnn3I31If7,<r7J(z*zc=<<LJ5en3fi}{|f{((g000000gggR$BBBg$B$B#D##R#DDDR88888R888mH\n" +
            "((}n[uf11ulneexyYeYZYjjxax*><'c||(||Fi{fI1lu[[[[uu3!>><=<>+!r!!rrc*cc*c*///z*z/?s/*r+c??c=->;>=:_;++=;cc)(7naS]unt[3ejE]yYZenul3IfC!;+7J7F>crc<!*ifnnIIF}i|fCilg0000g0gB$8BB$BB$B#$##DDRD8RRRm88m88H8mHH\n" +
            "||}nnu3I3tnn55xjyYxx5Zoya]?_*,'s|||(|FF{{fI1[Z5ZneYZl/>><<+++++!rc*cccc***/*/zzzz**cc!r>^_'^=;_:=++;;+/T(JJna]eCf?Ju1Y2axoenut1}}}{)^r*)v)+^c!c/L(ienIIFf{|f{|D00gg0gggBD8$BBB$$$$##DDDRD8R88m8mm88XmHHH\n" +
            "||fnntI}3tnoZxYxjjYx55ZjjxC^/c:,|F{{C{iFiCt5nuuCiu[1CiT;;<<++!!rrc*c*c*cc*/*//z/*cr++<>^,_:==_:^+r;^>c*[{7soY5[|C}cJ3nxYeuul13ICCCi7!</JvJv+,>c!*v5nnII|f{|f{|BgggggBBB$mDBB$$#$D#DDDR88R88m8XmmHmmXHXHX\n" +
            "|i}n[t}IIto5Z5Zx5jyy5eZY5YZ<**+_+iF{{{{iii}3I3I}1f{1Ii7!=+>+++!c!rccccrrr!rc**//c!<>>=,,_:_,,:,>!;=+r/sv|v*oeea5u1131xoontffI1I}C{|)!;T(Lvv/<;^^!FZn[fI(IiFfF[gBBBBBgB$DH#$$#$D#DDRRRRm8888HmXHmXXXXXXXX\n" +
            "FF}nn1}3tlZ5YYx5yyyajYx5YZyLsz/>-rFFiFiiiIC{fC{ftFu[nCFv!+)I!!!cccccrcrr!rccc**r!<;=^^,,,::,::^!+!+//s({|v*n[y]yZYe[5Yoent}{I3I}{{|)c=?)Ts7J!+>=>s[n[}I(Ii|}|#BBBBBBBB$88#D#$###RRR888m8mmmHXXXXXHXXXAKK\n" +
            "||}n[1CfInZ5YZ[[[xyyyY555x5YsL*c=':vFFiFiu1FfC{fI(ut3tAUOXp9(Lv!*cccccr!++!rr++++>;=,,,_:'___=>+<!czsT{Ivz*u[fF{ttnnnen[u1}}113f{{|7J=rJFT/7Jc>=?Tonu}I(Ii(C{#DDD88mmmKUUKKAKAUGGGbGOppOpOpVVVVpppOOOOOO\n" +
            "7|}enlC1I[oZY5n[nYyyye555yy]2[r*c>^:!8BBBqfF{C|C|J[t{fU8DR#$8GdPJ*rrc!!*+!c!+><<>==^^^_:''_:,;<+>+!!+7)Tvs*Itv;L()?(3ttIt3}I113f{iF77;!7{)J*/T?sTTye[}I|Ii|CAKKAAUAUUGGVppppVVV4999hhhhh6PkkqqkqqwSSSSS2\n" +
            "(|Ce[uCllleZ5eeZoYjaxjj55jj]]xao7>>!wD#$Dd{|Fi7F()f{){S9GbUK8RmA5ccrc!!rr+!<>=;=^==,,,:'.-:'^;=_'-'__+7i|T!{1)Tr+>>*vC3}t3}I113ICFFJL,=J(7v**r*s?z5n[}3|3i|{AXKKUUUUbOpV444V4999hh9hhh9h6PkkqqwwSwSSEEE2\n" +
            "i7fe[u311uoo5nneo5xYY5xjy]ya]2]jayaE8#$#$A|T((J|7T|vv}nxwbKKKKp62*c!!!!r!+>;;^=^,^,,__:---___:_<!rr/z+s|J+^)11{C1}{iiC|}lI}331IfC|(v)+z7)(7)!rz/LJ[e[C3|Ii|[6dXRg0gg00gm000gBB$BDD8mXAUOp49PkS2ajYZZel11\n" +
            "iFien1}f1nZ5Y5nnoYayyyo5xa]]6HKXxx2UD$$$DUZ7LvsTvT)Jvvzz*OXKKAOORZz!<+!!++>;=^,^___::_-..''-^+_^+c/r^77fCz/Tl[fF(Fi}f}I}f}f313fC{|JvT!(7)vJL*c+</s5e[{3(Ii({F}aOBNWWWQ%%%%&%%%%%QQQQWWWNNNNNNNNNN00NMGZ5\n" +
            "i{(tnlf33[n5x5ZxayaaYoZYYaE6ESw6G9y8$$$$8O]C7z?s?s/)7zzrrOKKAAGpbdE!rc+!+<>>;=^,_,_:::---:-=>J|ii|!zsz>Lvvsz[Zne[lt3I}C3Cf}33}C{i|*7TT77)vJz/*c</sYe[{I(fF|CF|(fCi7{C|{i7|f}C3[xShdVGKHmmmRm8DRRBBBggday\n" +
            "Fi(Fnutu[ZoYjyyx22ajay5Yxjk2ajxxqVX8#$$DHV][3(+<><)F/!+rcpKKKAGpVqE*+<!+<>;=^^^,,,_:-'?5eelI}{{{((|+zzz!LJ/4Zyjy5e[t1tl3}fIIf}C{|v;(J7FTsJvz?c+>czxe[iI(fF|CF((I{iJ}i({i(F}|)v)J|}|vL}i((vsC(7)7{7?)SXp6\n" +
            "iiCTIuneo5Yxyya]2SSqqqkkk9q666P6PPXmR#$DHd]yt|<<!><+>+cr/UAK8KOpp4e}!<<<+>>=,_,,_:'--/oe33}ul(((7J)v<*/LsF(LYx5xnnul[l1ff3If}CiiJ*=sJivs?T///+=>csZn[{f(}i|C|(73{i7}i7CF(iC|)Tv)|}|vT}i(7Ts}|J77f(u[u[oo\n" +
            "F{{T}1[oZYa]SwkkPhhh64d9hP44dddd4GAmR#$DDA4dwtJ{V44hqy[np88KRUOpVpwE+++!<;^=,:_,::--.ueu}f{]2l7)vvTTc</+cs[r{nt1Itt[uIII3II}{i|7zc+(37//*c/cc+!c7oe[[{}(fFFCi(73iiJ}|7i|(iC|)Tv)FC|Ls}i7(TL}()79m$mg8AV4\n" +
            "F{C7|J(Fi{}}fffI331111o6x[uunnneZbAH8R$$D$RyyYYYYjxjyySRBBBDHUGOp4V6!<++>;^,____''--'{n3(?ZY2[Czz/z/c+*<cc|(Tt}C}}}f}{}}C{{i|()LLc*|5Tc/!?/+==<?Y]5n[{f(fiiCF(73i|v}F({((iCi)Tv)|{(sL}i7(TT}()]AXe9UG4pb\n" +
            "F{CFv}1[eoZZ55Y55ZZZZyh5oZZoZZee]UHHm8RDD$#D#DDRRD8#DD#$B$DR8XAGpVp41>>>>^^,__:'----,ES5L!|Sn{v?cc*c+,c<!!=[)?}CiiiFiF(F(()vv))vs!=sn*c/!*+*<<!r]2Yn[if(fFi{|((1{|J}F({(({CFvvv)|{(sL}i7(LL}(J$hUeXURHDB\n" +
            "|i}CLfteZYjyya]22222ES2EEEESSSESOUHHXXHHHHHHmXXXXXHXXHXXKKKKKAUbOppOh<<>;^,,'::::rj$Ge|ZoyY37T?s!!+<^:!!=kKhxT?/iFF|(7(77777(((J?!^?S<>c<<*!*=!xEEYnuif|}|ii|(71i|J}F7{((CC|vTvv{i(ss}F7(Lv}7J7KA$g0gM0R\n" +
            "|iCCv}teYjya]2EEEESSw2qwwwwwqqqkpXHHXXmmmmHHmXHXXXXXHmXKKKKKAAUbGGpdd<>>==^_<lwDHGK8VmEvsvL?//c?+<>^_'<^42}iJ|I+C()77(((J7|(|(77L!=(f+;^<c!c;rn2SSYn[{}|}|ii|(71{|J}|7{((C{|)TTv{i7sT}i7(LvC7J7gMM$8D8bU\n" +
            "iF{CvF1exya]EESwwwwqqqqqqqqqqqk4GXHm8mHXHXHHHXKKKKKH8HXAAXXAAAAAAUUUb*=spdKpKKqURkXKmU}=>++!r/!!<=,'':!LUx7)){nfC{7(|((7((|i|(7(L*<LJ=>++rr=zxEESS5n[i}FC|Ci|771i()}|7i(7}{|vTvv{i7?vfF7|LJC7774mbO4d66P\n" +
            "iF{C()1Za2EwqqqkkP6PkPPPPPPPPPPpXKKXKAKXKKXXKXXXKKXXKKKKKKKKHXUd6dGXmRG]ApXbXVdpDHUGbEl=<!*+cc*r>_-.-_*KO]LLT{3}}}77(|i(FFFi|FiFsz<=,^=^=^<7]2ESSS5nui}F}|{i|7J3i(JC|7{(7{i|TT))Ci7?Jfi7|L7C(7{aOph6hhww\n" +
            "{i{}i}]ySqPh4VbKhd26ddddddddddd4kEGKXXXXHXXXXXKXKKKXHKKKAAUUbbbbOGGGGXbwbUXAGHKKmd9k]u?!!+</*/r+_--':^!UOh(zsv(73I|7(FF|iFiFF{i|)L/>;,_^^=7y2S2SSS5nuFfiC|Ci|7(1i7)CF(i7J{F|vTvv}iJsJ}i7(L7}(7kAOb4Vhh9d\n" +
            "KmmXKXHHKHApGH8XHKbGV9P66Pkq6666hd49pXXXHmXHHXKXXXXm8HKAAAUAUbGOGOppV4O49Ap8XHAK84Gxof<>>cr/c:--:_,hXRHAUGa*ccsI1}|7(FF(i|F|i|||7)sss*^+Ixx22ESSSSZnli}iC|}i|7(3i7)C|7{77{i|v)v)}FJzJ}|77?7C75OmAAOHVOOG\n" +
            "mmXKXXXAXXmXXmHR8888XUKXAKH8##8K8#D8XXRVGHdbDDAH8Oj3I313tl1uu[ll133ff{}tSK8mVGbK2OE[e3!+=<+TGe_-w88mmmKGAAqz{1on1fF7(||(FFFiii||777J)Js*F[y22ESESEZnuiCiC|}ii7(3iJJC|(i77Ci|)))J}FJ/JC|7(s7CFGRHKAXMUAAK\n" +
            "49pGX8DDRH8XHmmDDRmXKmDRR8GphEwGRDRmG8$$DmmAURR9hmXhpUABDUkRXmmDAmRAdARmHRmAX4AedEfZu/c+r//I2ZSm8UmHHHUGAAV(teZn1Ii(iFiiFiiiii|((|(77Jv<vC3oESEESSZ[liC{C(}||J(f{J7{7|i7(CiF)v)J}FJz)C(7(s(xDgDRgRggXXXX\n" +
            "KDDDR8R8U9jY4XAGp4dVhX8O9Y6HKG6PSaxkKXUUO6HRKmXhXmhqKGykSXGVYbUdpXREm8428U6XU6nEStIC1!LT!>vee9HKUGXKKXUGAAO[o5Zut3{iii{{iF|i||F(|i||||JL,7}uYSEEEEZnu{CiC(}FFJ|f{J({(|F77{F|)vJJ}i)zv}|((sp$0#g0g0MD0Xmm\n" +
            "u[[nnxSq9VGkEEof[y9w2S]jykd96ejV99ayxnZjpUU4k6AAAKp9blhSa5xhqq}6ww69peS5Ii5qEaYti}{}JzT)?*Yj6KKpdOKUKXUGAAUqZ5x[l}C{C{i{Fi{iFiF|i{i{iiF(v_Fuyx2EEEo[t{C{C(}FFJ|}i)|i||F7({F|vvJJfi)zvf6XD$g0$BgggMNDNM88\n" +
            "oa2]aj55ZYYxy4dVwqStIZqkaaaa]2dh6eYhdPx5[llZpKpbhKG6a2h1wE[u[6wSIwakyhxoj1[xk[CiIIC(zv(JscY5HXUhdbGbKUGOUUK4nZ5ut}}C{{{{F|||Fiii{iiCCC{F7ss9qEaEE2o[tCCCC(}i|J|}F)|i(iF77{||vv)JI|vz7kmgM00gggg00NgKHAR$\n" +
            "yEaj[I|q9kyeZYonY996wqyt}ekEYaw]2h9d][qh6ayj552K8VyhSyy6w3PnZZYSk51hEaexxey]IjI(If(vs(i7J![UXKph4GGUUbUUGUAGneeu13IfC{ii|FF||i{CCC}}f}I3f99qayaEEEo[lC{Ci7}i|JifFv|i7FF7(C|(vvv)IF)?J4ObKAm00mD#R$XU8KXm\n" +
            "ZZZ[lneqhY3eqw]eeu[eZ69hw6kZIeS2yyaSyShhyeqUUA6qY26k[pSyxESihueoa4PtZ[nn]aYuqx}|i)T(?|{7(>lXKOd9dGUKGUbUUUAAh1oullIf{FiiFFiiFiC{CfI33t]d6w]ayxj]S2e[t}{CC|fF|J{}|vii(i|(|C||LvvJIFvz{AGU$OKm8UAKAAGVG4AH\n" +
            "YyaajYoyyj]qhwu3]kq5[ZeooxP44dhZ1[]]22]2kpKXpSVGVy1x62Z9E]j2S1heYZkEx(ZyloSek}f{T7sFs7|JirpKUpddVbbAUUUUUUUKO[nZnuIIi(7Fiii{iCC}3I3n94hw]]]]2xjyE2Zn1{}C{(}F|){C|v{i7i|((CF(vvvJI|vz{bUHmKHUDBNMgUUKmO9U\n" +
            "o5Ynenu5E]SS]aj2kP5{eS62Zue5ZYhAG4we1eq964ObbGUknwwxokqE54ayek]36[Zna5|uejI]23})Tvc|/7iBROGXUVddVUAKUAUAbbbUbd1Yu1utCFiCiC}}}ffI3qO4Pw22]2Sq6ww2aEnn1CCC{7}i|)CC|vii7i(7({|(Tvv7IFTs|VbXRm$0000M0HAXmpPO\n" +
            "ItZox]xjoZS9h]22yjxSwkoi5wwyenojxPAAh6yelwddOVq26xj22lYwjZ2kay5hxwyII5xuu126u17LLvrJcBBR#mRDKOVVOUAKKUAAUbGbUG[uene[IIIII3I3I1]pV4PqS22SPd4VV46wy2e[1C{C{7}i7J}{(v{i7{|(|C|(Tv)(fFT?(VVUGUAbRmgg$8mmgBmR\n" +
            "ennlu3CI2w]Sxeak6w]2ay5xqqStC]wy5e52awGpGphYeaSa]a9[Ekaox6]oaw2y2dCy}It5ZC9ou5T/TT/wBgB$B$D$0#4pGAKXKAAUAUGGUA4tYYeon[t1l2GpOV9h6kwwwkkdpGbOO4Ejy]e[1}{}C7}i()}{|JCi({F7|C|(vv)(IFL?(4Op4OAXKKKpK8GU96]O\n" +
            "1nYay]]2n1n2Pw2E]nZ]h6w]2]oj2k6ZI29SxZYwwdKXb2I3yxY2kthEj}yq]YS6SE]ZtIe[J2Yn2CTsvkbX$#mR#ggXg$GOAUHHXKAAUAUAUKK6neeenqGpp99hP6PkwSSqP9VOOGbwPVdPq2Z[1}i}{7}i()}{(v}i({(7FC|(Tv)FI|Lz|EhbdGpGUApp4VGdw6]w\n" +
            "]2aj2yZlayY13ll2PPS]ae5whw]]]ay2k9kIxPhwxaqkydAy|aya]]xxSj[IqaqopEZexCuu[ExqqqLJ)C#$8ADBgBXK8XpOAXHHHKAAKKAV{FCSObGpVph966PqqqqPkkPhVpbGbwOOO4d66wx[1}{}{7}i()f{7v}{({((F{((T))ifFLzi]d$Ob9UVP9Odh4phUhS\n" +
            "eooneeoYS5kP6axet3ux6hS]25lx9dwk6EyEP4jtjdVwYj]24Zi2yExPo]da1lhSeyS[n1l(SSjj2qSqP6q]GYhX9ROA9{tOAHmmXKKAAbf{CIlnd9hkkPkqkqkqkkqkkPhpbbAdUUbUGO4d2Syu1C{f{7}i(JI{7)fi7{(7F{|JT))|I|szFYdHm9A9dpU9bHw44mKp\n" +
            "I3eYxYxxY2kEue6hhEYx[t3n]kwSwwoa9hkSkkw6pO9et6PqqPOn1qS]yPeE2x[Y6]tx3u7[eoe1[nnejyf(v777JJ7Ls1pbXmmmXAAAo2Sxe[5]wP6qqwqqqP6P666PdVOUGVUbbGUUG6k4P2]u3}i}{(CF(7f{J)f|7i((i{|JTv)F}(s?ie9p8UGdA9p4VbykOUZE\n" +
            "yyxntICi3yaaq662lYkdP]aet3Iuwd6Sky56G6P9kq2kddyCE9hhPfxaa]2qn9]xu]oCttCe3C}|FFfF]o33{CtjS]x]j6bUmHHmKAKIqbbp4969Ph6kwkkPqP66hhd9VpOUUbbUAUpqpO46kwxoI}if{(CF7JICJ)}F(i(7i{FJvvvi}(LzFZ9dm8PpVh9AX6OUVpHB\n" +
            "teYj2y]]2n]xYaay2h6qY[]96Syxett5PVd49Ewh6h69q2aEq]}nE9]f]axjP2xkxf11}(tIq]1[{Tv|f3}t11eZney2aSSwVKXXAAf3dbUUOV44hw99kPPkP666994VOGdKpGbbUkG6hhh6kS]]I}ifi(}FJ)f{J)CF7F(7{F|JTvvFC(zzindVVmGAAUGUkS64SybY\n" +
            "313133t1ujxfoww]]aa]ywhdwen6h9wy5ulnPKbpdw]k9q2S]yyyal[w9uwayEP5dZCCnIiaxZjySSyjo1}1u3FF{1unult[Yy]2h5ew4OUAAUbpVdOpV4dhh9h9ddVVp4GpdGUVwwE9ddhP9E]af}iCi7CFJ)f{))CF7i(7{i()Tvv{C7??il49E69U9dUwhAEyUK5h\n" +
            "JF1Y5y]2YeySw]t}tw62y2]aj]kh9aZhO4SxYntuPAdPEEex4Say5Za2eCak6yaEP1o3|3iCItfCtlI[I]n7Yj[(/+TFi}I[eZojj]2EV4pUUAUUOp9ObOpV4d44dVdlAOV9OAV]2POGV9qq62]yx11f{|}|)JIiJJ}F|i7JC{()TvvC}7??{YPE9py#ppSOV]bUSP9d\n" +
            "Yya2SSyoC}5Z2kPwSju3t]w]YxY22q9pSnw4ddwwxI1]GV9wana9255un2a}3EwSyY[tt|C77||(|Fi|ifFzojn1ZZn}vv|}1l[eZ]]Zxd4OUAAAUGdAUbOppOpp45wAp9hpApSyqVOVhPPkqayjY7iv|CCuZ5]PeeadZCFi3{(vvvvC}7?z{5Vd]6hdw9hdw]qwoyOx\n" +
            "xyx5enn[nYjeeenejPhkqq5}}5Eq]2SqS6VG9o2ppVqaZ}JISS]][56x5ottYY|i55fiF(Yn1(JTLssz??z/vFiFuooYxxy[{(}Itu[[lS9dpGbAAAOGbbGGV6Ex2VAph6pUGqa2VGp9qwwqqyxxYT)(CElIFTL}$NRmWmK75q|vTJT{CJs?ix2SawqES]wyk9xEVhj9\n" +
            "[oYay]EaYYjuSwxeeneeewh4q]aefnkkS26kP94ppa5kq22Yl}{naaxeoyY[u3ta2itt|lfe[1ifJvvvvvTszLJxviIIn[Z5xyy2xen1ltY9dd4pGUA4KAVKd9VR9p4h6GUOk5Yhbp6Swqqq6yjY5)TL6(}T!rt3|dRKe5hj{6|vLJSCC7??{[hPS9Sb6S6]a2YEaxy]\n" +
            "jjYe5Z[[llYywu}xkkxonneexq69w2Y1t]hhq6wPqh9kalf}fII3CioZZlC35Yyan1t|I)Zn5JJ{v)TTvszzTxGO9TL(|If3tn5j]ayut1[ohhhd4VGOOSOt5YnSAjqdOUUhjohU4w2EqqkSSyxxYnvkTrC+vI{JuLZ62s[2/f2oy(11f7(9652hhUGKAKAOd9Sk]2E4\n" +
            "3ftuZZa]2yyjyZjkkj}tq6]ZneeZe]hkw62uuaV6qwaYZoneZZo5n[[[tIe]e[yd3t||JttjYyFCm?*z?LT2XbGObKvLvL7{i{CI[ZZuII1eoh6k9994djY1ppS5kdSUbbd]ZV4wE2qh6PkS2yxY5eyS+>?1xnf}i_*I[c>JrFU]l|Y}ly9Ply25x]EqkS66qqkkwE64\n" +
            "eoeelnu1f}}e2j2ay5xwkwl}]k6joe5ZoSdPPwSlte2E5nu1uttut3fIIfff}oE5le|f}Fue]CC7Js/L7I3kAOVGUAUivLu/)v|CF}t1Itftne6PPkqqSEw9BXhfy4yOU9EYkqaywhdh6PwSyyYaySE[>,1jlv}(*_^+Lc=,!>J5JJ3i|kbAoEwYq]2ES]qE49Yhy]ah\n" +
            "2]ww2Ea2ay5ex5kkqyEyxxxSP]liShkZneo5YShkSaZfFFi{CII1lun[[u[no[[}uouJiia5n{q)TTL(Ci3fVOVGUUAAAJZt|}ffi|}ftI11uoahPh6q2x5w]hk{Sd5AdS5a2yaPdddhkqSEx5ax5[y7<;e]5xlte_::^+,_>=zkJ)TC1[Zxhh95haa]]a264wj6axSh\n" +
            "wq2SSE]jYe1uy22YeZE6E2ya]yjaq6ZI2h2Zo5o[5jSEaxZooZoee[utt1}}F}C3Ye3i|13Zi]bt3vv7TJ35eOVbGGUAApts7CtxYt}}f3311u[hdV49klAnaSStdyUpq55yxShd4d6kqS2]yj]xj]Y><^l1}i|Jf[;'.;'-,((uiJTt7Con299Zd]eYEZYqwS]k]qjw\n" +
            "ZxxY5Y5xe5Zj]3oPwqy2o[xSdEyy2ayEd9[f]66Yuut[3ICf}C331l115ySSPhxx5(f|(ioi5ye*fJs?vF3fIOpGOGGUAK2Jvv7i}113}fl1IlueVOOGVk}2eYtOuUpS5eo]h4pp9d6qSaxxSnnYx1t,c_TJT(J)?r--._'-;)|ja(vv({PfFnx]yYue]tx]2aY6q6]x\n" +
            "Y]2EwqS]y5Z5oa5t3f[S6wjSy[n]dkSwEyy2kwZ|3awYZ1ICC1}FfC{3ItonZZZYx|{7(I[55V1=|TTs)itcjpppVOGUAKA[iIC)77F{}F11If316pUbpXYltZdZUVqyno]9VGV9kPSwaw6w]xh[F{55r^_<^,-^'--.`'.-+5555iTvJF1}F3Y2oe35xt5ya5ZPjSZa\n" +
            "j5oot1}C(|ifnYqwy5o131ZwkqS]ae]d9qw2xYa2SSZCJLT7(3unulnY22waZn[y]Z}|(31ZS6acJLLs)|C3q9OpOpOGAAUAFFFF7((|Cf}f33}t[pOGV333}n5UOw21n]dpd9PSE22e52]Skwe{i3Ylf:,::-'':'^<r-',CujZe[v7Fuu]JiZ2Zn3eo1eoYn[weyly\n" +
            "S22EES]E2]]yyaweowPkExZuI3uxwPkkjoS9h2225ltut31t[lttun5Y]a]YeIn[FeCv(1]alk]zs7LsT7J1y6GOVpOOUUAAKvJ(CC{i{{{CI33ltZ4ObU4qykUpw]1n]kqwSaa]E2Pk26ab5}31feZ[l==_'--',><;r-+Je(YtiiC3vJzcL)1xt[I1u3tt[t1xeot2\n" +
            "u1ttl[lu[tul[ejyq69juyqP6]5Ynu[]9qw]YnY]EyyYn[tluuul[e5eYZZ5yyZjF2i(oooCnwk?9(TLTf+e29GppVVpObUUAK()vJ(|FiCi}}1I3lbOpOGbUbdwy5[5a2ESSqqq2x9Sqkq113YZ5}nuC//r_--.-'--+rzTv)Ts?TssLc}5LTCuIeune3nlul1e[u3E\n" +
            "]]EESa]55ntltlxyYyxEkqPxnj9Pw2yZutx6S]2Y53f}C3ll[enoj]SqSqEayZ5aun{xxeCxCE2<+)1FvznuYOpV4dd4OGUUAAKpTJ7|7|{ii3131loppbAAG9Sy5Iexq699hhhZ]kd6bSe}ly]e1xY1Is/?r''-':>.+i</vTvz*L+r?z(c*zJ1en56e1aute1Z[luy\n" +
            "w2EayY5[}i|{tnyuEwa]jx]yww6qojh4waYl313eYjY[nelln[[nex5x5e5ZuY5{loey3Ca(7a5y<+*vZt1lo9h666P64pOUUAUUAKqTF|IC}{{C1luXXKAph2jn(1oSP94p4PEqw8Ebx}1yEkZed]w[f7(CJc;__,(''</='>:-':<c!5uzL!![PPuUxe2e3yZu5Z[[\n" +
            "ajYZnneeene5ZyES21f[]qjaYaxywh9EnEPkEZentC|7J77J((|C3n]w66dqYux{x5[33l7v5ynI{{{}I3tu5]wEEEEq6h4VGAUUUUKHvTiIIfCI3l[3bG4q]5l)C[yq69d9Ewk4V96YI1obkZyhwnoyYf}3(/<==cFs._cr<^.-,<FiyzF)eZxx2OE6]Y6ayPa[2jZe\n" +
            "ayaayYYY5xxxyYYxqq2]Yl}[]q2j222qd9keuaESYj[nt1Itt1lloouuluuFloyxjtC3[T)}qy[}iFFC}I3txS]yyyy2wk6d4pGUbUGU7v7CCCCIl[3xV9w]Y[oJ1eSP66P]yw6#XA5leqdkyn5wqYy5oxuCTszcc?3I;:-*<-.^|Tr{**/?l[y7jSqkwZqkhqSw]]ey\n" +
            "e[[ul[tltlnZYy2alo52wqk2aeI1ZPq22aYYwEa]Z3}{{i{C3[YESwhddk6YnZeZIFxCJL{IwynIF||iC}I3nwxnnnnYaEwP6d4ObbGpV)v7IiC}o6SGdPjZox5Jtxwh9d]kd9AOpu3yqVY2[nn1|2]1x53vs?zzT)7v/_-F;^3[uevsr*FT)(IYq9dhEa]2a2]2xxyS\n" +
            "qq2]]ajajyjjxZxx]Yu1[e[oPqqq2]3[qEjYejenoulu[leZY]SqPh6Sw]n5Z]a3CaFTLf{v]y[}FFiFi}}IuouIfffunZYy2qP6d4V49}h31D0%GUqKP6q6VD8AbA8U4yqwU6GS3eYpdxxVt{()72]ZY3)IiL?TJLvs/!:(!{Cz=!+!rt*{I{/+hb6SPdwkqSaxoey5\n" +
            "I}i{Fiii{|i{}3teaCo6kYu1[eee29PPSnF}exyZullt3llu[ojyyE22qye5xt|nCiT(3CT[jx[}|(((FC}fI3(Fi||}3t[eZx]wk6h6V1b1{2N%MVhd666kpgRmUkGVAVjddKYl5qHqy64Vn}FT(a5l}(3fIJsz/c**cc*Jl]!>+rcsJFIIFiJ!|dk2h49]9wE6y]hw\n" +
            "y]]]aayayZ5eenetlywSC}xq2xunoZySkwS]xni(JJ777CC[5SESwqS]5Z]1f{aFJv|3iZ5axxu}(7(|{i7z(C/=)7/;{(TiIl13[jnfi{)teZqmRhk]9]526BmKOVXKdSa2K[Cx]XaaG6Ve5lCJv]1Iu{f]u/*rr!+!+cz(1!>;cz)3vin3v)fctK6E9OhdG6Sq2]SP\n" +
            "eenu1111It1[5yE2SyjYY2kSa{129SZZo[[uoaaY5e[eene5yy5Z[uxjat{{aCis){I{YSI2y5[f|(7|i7QrI/0<&'&r0<1r$}7L7iX|W1|!elOYY[@{EQ}V2DKU6dbGb9]Xe}oSma2mPh[xxn3|Tx5I)I|v*!!+><>;>+*)I?//cz(LYC1ss|3urIK6]Vd9qqwwPqSa\n" +
            "ZeZ5eZYYxyawSSw2Eyq2aSxZoZ]6Pu59qZ}1f}{C}C}}f1[Zawqxe5PuF}aC(LTFC{yeoi2a]onfi|J%JJN)M*%>c g?D!t*%FLwP({{Nf8%(YAuZ5%aPQ$&qHDDmR8bOqG51yk8a]OPSk2lye1{7}21svsc+>r>=;>;;>!)[|F7c!**fJtL*T3Ic<Vw]9dPPqSkwSqS\n" +
            "[onZYe5ZunZo5ex55Y5ea6q2jyayYE62({te[e[uII{}}tlneyaE2oiC5CJTTT{1{xl3eS22an[IF|7T(|2QM+NH%r%b%s%%J}7IC|N$mjt[tuO3xaWQN%Ygk$mOdbbG2Gllo]m[yKPkk45xxe1{7)n7/sJ+>;<======;+|n3}i/*LtsCl7r!*z}i[pS29d6]h2wV2w\n" +
            "J|F|F}f}Il[xxEEwaxy]]y5[ZyqkSk2enunoZnlf7v2awEaw[J7t5t}(7zzv{I3u[e|6E]kyjutIi|((Fii|()s=LFC}}{|iCf}C}CIIuenZnxxhk9Op2Sk9ppO6aPdhO5IaYGyZUP2a9P35eu3iv?n*/?/<;>;==^====<![7c=Ivjs?111+!+_+:'k92PdShS66]kS\n" +
            "]ya]aEy]aayjaxY[I{[It]Syyy]yoq6axjaxjyEw2wl[()7J77((sT//)Ji}I)yu3ZwEajqS]etI{F|||i{i|)T+(I1t11IffffIff}3tnZY550%Qkwm5Y]qdX929K495IjxP]jApYaYSa5ntf{Js+/(^!+<>=^,===^==><{L>>)[(7o[7c/^__='^<4V266kdPkh2k\n" +
            "II31tlunZYYE]ESk]22aZ3{ItYkq]ao[ZE2kSji1vv)vTLLL?/*c??)JItC(f[x{]wPajxyS2ytI{i|FFii(7LL^FI1t1ll133II}IIIftnZeoO%&N2ifxdad888gNP5Ijjha2O9yxxonE[1IC|L+'{3J<=<;=^==^^====+T}cc>}jqk{i,,__;<_^^S4wq96hhhkSS\n" +
            "ItIl1t1}}13u1Ilu[[lewk2ZZnt3taSa]22Ivs)((|JLvzss?TTJiCIC()75[l{q]qawxxSyEYu3}{iiFiF(vs*zC1u[5xjYeu1113131f1l[n50%W[uno]bDBRBQRe35a9nadVYk5Ynt2nfi(J*:-vF}v_+^+<>>=^^^==+s7CCJL<=<;Fevf!=>^^>;bUddhhPS62S\n" +
            "i{}31lu[[o5]SPkw]xZawYtoxqhSyZ3e[1TF(JT?|(7T*|7iCC}CF(JT1[u[vy6kjSkYeS52Zao3}CCCii|7zv{no5o[ttl[ZYxjj5e[t}fu[no5%&XjuwdOM#D%D5u5yqjYV4YPqxZe[yl1(v!__>}7F_=;>+=;=^^^^=^;zv!<+!!+!J(JT!>;+>;+<{Vp9h4696hq\n" +
            "l55jjYjayjSqwSy2j[CfxyaESw2n2h[{tv(vvi)Ji|T(sJJ7J)J)Cyn{oeJojay]]qEZnnZxn5olfC}C{i(J{3tnut3}CiiiF(if3ueZZ[CIle5eoWQPn1nY6R0gu[o26xkpOZ6xY[n[[aY)J=zTLL>,!'>=!>^;==^^^^=>/(7Lzcz*rLJJT/<+!<+<(ZP94Vkkw2Ek\n" +
            "oenYYxYo[Z]yYxZnoZZxyZYYYx]hh2IivsT/sLsLL?vFs?!zL7eCCYoCL[ZxEy5jyYaeutynl[ZeIC}}C{(vTC1l[lt3}Cii{{ii|J)}3tIC3[eulH%Beu4oSh#n[1jqYdVVj]]Znu1uujn|/TTT{s!;^;>>===<;==^=^^=*(k7??(LzzLv?Tc<+><!]onwq66S]xEa\n" +
            "luYYxEayakqSSwkSSy]ax}x2u/*cccccr!cc*c**/LL(c?Jvs3Cv)}Iu[[nYynn[y5Y[1lx[1ltteIff}}(sF{}tn5Y5en[lt3IIfICCFJ){CtuunaNBKbAGUD[nladuOqhZPSyow2not[[tvzz/cr+>=<;+^,;^_::_^^==!(F7!zL/*r?LLczc!>+!xIojXpYOE96E\n" +
            "uuI3}I{{C{t1lZZnlnx2S]j!!+<<><><>++++++++r!!rr/Js(L7II3I3tu[ZeZnZ[nt3lZl131enuIII{f3ltt13nxyjjjxYZonen[[ul3I{3Zjjx]X0DROEIlYqqYV29ExPyy2xnujyouuu+r*c+>=<;+<,,-c<L<',=^=<(7)<zzz*rcJT///z!r|FYYdkPS6P6Yw\n" +
            "J(}tZaayeeI{{|7(FIoxZC!+<>====;==>><>><+>++++++!/!*)CI33I311un1Zltn3}Iet333l[55ZuZueoZnuuC3e5jaayx5ZZZoee[[tCxwyn2hdNgmAfIawyoOSkyxq]jyay1tY5nnn[fJ/+;>==<r=,,-?LcLr_^,^<zTTrcL?*cs/sv(T)!sJT356Op6[]n1x\n";
  }

  private static String getDefaultInvertedAltAscii() {
    return "hd2]x2]ExjeuZu13t[31utu1IIlwd94OVwwSwkd4hV9VObGVKXXKUAbbUbUGVH0#UAdpUAAKHHm8m$BgBXUAUGppp9PSi{{{}3[Zn2oS69SenawhpphkhaoYSPP9qEhwYwdwnZxy]a2SS22kP2xnxy2]PSq62wkkP2awwx`    `...-.....-.-''''''':':':__^,\n" +
            "6d2aYEa2yYe5lt1133ntluuf3f1Sdd4OVwwSw6hG44UVpbGOKXKXAUbbbUUbVX08$KGpVObUAHm8g0ggmAHUUbGOpdh9q{}}}}l5nSewhhSonawhpVdkhao5SP66q24EYwd2eYxya]SwE22kq]Y[jj2]PSqk2wqkk]]wq*`    `...-.......-''''':-:::::__^,\n" +
            "Pd2axS]2ajeue[ululI3e[[tuut]p99pVSwEE66UGp4GObKOKmXKAUUbUbKUVXB8RB8XKXKXmm8M00g8mRHKUAbGOV4d9qyIC}nZnwZqhhSe[yw64Vdq6aZ5whhPkSV]xk42n5xy]]Sq2]Skw]5nxjE2hSqk]wqkk]awk. `   ``..-.......---''':'':::_::__\n" +
            "642jYwEaxZultt1333If1ttut[1ad9dV4SwESk9VUGpAOUKGKmHKAUUbUAKbOKDDDRR#B8XH$NMM0B$RHmKAUUAOOpVd9h6Pn}e5nqok99SenywhOV9wkyenakPkkSpajkdjeYxy]wwq]2SkwaZ[xjE]hEkk2wkkka2qw.`   ``...-....----'-'''''::'::::__\n" +
            "69]jYwS]aZ[ulZZn1III1[u[ut[2999p4ww2q69dAAGAXKXbKHXAAAbUAXHGOKRDm8R8DBg00M00B$DRmHHKAKGbGOpVVVd9P]ZZnkZ6h9SonywhVVdwxZe[5qESYyk5ykdxn5xySSSq2ESkSyonxxE]hSqk]qkPk]2qZ.    ``..--....----'''-''::_:::___,\n" +
            "qd]yYSS]ao[[n5Zon3I33ul[u1ly9hVpVwwwkhVVbXAbHmKAHmXKUbbUXKAVGA8$RmD8RDD$BBgB$$$DmmmHXKUUbGGbGOppd9EZZPohh9wo[yk6PwwSajYxEa2xoyE2]k6xeYj]SSwqE2SkSyenxxE]hSqk2wkkkjSq>   ````.--..-...--''-'-':::_:::_,,,\n" +
            "qd]x52wyaonuleenu3333utut31ydhVOpw2wqPdV48XbHmUAHHXUUbbKKAUpGA8#mm888RR#$BB$##RRmmXXKAUGGObUbOppOV95owZ9hhSZn]wYa]wqdAdk4EEySxSwEqExZ5x2wSSSEESkEjnnxxS2PEqk2wkkkyEq. ` ```...-..-.----''''''-'::':_:^_,\n" +
            "q42x5ySxyY5neenne11t[1Iu1ItyVdVOVS]2qk6VOX8XmmGAXKAUbOUKKAUOGUHRR8mmmR#$D#$$#RRmmHXKXKUUAUAAGOOObOph5SZ9hdwZn]2wqOOKUA8KOOVPyyq26hkwE2a2]]wS22wqEjenxjw]6Sqk2wq6kjSw` `````..-'..-.----'''''''::::___^_^\n" +
            "q42j5]EEy5nnueeen1131tetIf1x49VOV]kKDBDXUbD8K#GKmXKUppUKAUUGGAX88mHXmR8RRR8888mmmHHKKKKAKXAbppGAHUOVwwo999SZZ]qVb8D#RXHmXHGkOO6wwqdPwkSEaSkwEEwkEY[eYxw]hSqk2qkPqxwl` ``....-'--'------'''''::_______^_^\n" +
            "kd]xZSS2aYnuunne[33I1ue[[1IYd4VpdkAmD$B#KAXDm#UXmXAUObUAAbbGGAXm88XKKXmm8R8m8mXXHXXXKAAXKKUOVOUDmGGp4EZ994woakdU#gRRAUXXXKOXAGGOO9hkkqSqqSwSE]Sq2YueYxS26Sqk2qkkqyE>`.``...--:--'-----''':''':_______^_^\n" +
            "q42xY]w2aZo[utl1t3I1u[[u1I3jdVVpk4UKKKXD$8A$RHAHXKUbGAKKbUGUAAXH8mmKXHmHmHmmXHHHKHKXKKXKAUbOOURRAUAUpPZ9d4wxkkb#ggU4PVUGOpGUUH49Gd999kqk6hqS]awq]5ueYjw2PSkqEqkkqyS.`.``...-':-------':'':':::___::_,^,=\n" +
            "kdEj52]2aoZZnt131t[l1tn[llnad4dPSq9OGbKX8RHX#XAHHKbGUAKKAbGUAKXmXHHXXXKKXXmXHKHXmXAKAAKKUUGpGX$mAAXUUpxdVVq2kpK$B]xYaww]aS64GbX8mGOkV6qqqGVS22kq]5unYjw26SqqSkkkkyS........-:'-''---''::::_:::____,_,,,=\n" +
            "PdEYZ2ayyZ5nouutut1u1[u1uIuYhqSwqGXUppAXmD88#HKHHAObUAAUUbbbAXmmXXHXXXXKHXXXKXXXKXKKKAAAUbOOK$$XAXmUAK9dd4wwPVK05ultuntlloxwhOAmm84969qk6VGVESkwaZuoxjSS6SkqSkk6qy3.......-'_'-:'---''::'__:__,,__^_^^^=\n" +
            "kd2xZyyajjYonne[nlleuut13IIYkP6VKm8Hp4OGmR$8#XKHKUOGUAAKbbbUAXm8HHm8XHXKXXXXKKKKAKAKKKAUUOGOHgRKAmmbHHA94d]k96gjl1fff}f33l[ey6pXR8Dd9hhkhVppdqPwaouoYjwS6wkqEkk6w]'......'-'_':::--'':_::__,_^,,__=_;^^=\n" +
            "Pd2YYy]jxxY5eolt[[ln[uttIfIZd6bXmVVX8X8$R8D#DKXmAObUUUAAAAUAKHR8HKXXXXKHXHXXKKXXKAAKAKAUbGGUB0mXHRXAHR8OhhEh44Ket3IIf3I13t[eZySdHDB8GhPk4VOVpkkSyeloYxww6wkqSkkPS]-.....--'__''''''''___:,_^,^,,,,=_=^^=\n" +
            "k4SY5x]xxayZZ5euZolnel331IIY6XAwqh69GmgXR#BBgKXKbGUUAUAAAUAKKmR8mXXXXHXKXXKKKKKKAKKKKKKUGOUXM#XX88KU8D8Ud6qd4Rx[l}1333lt[n[o55a2VHBgUqwkVOAVOVwwjntZ5ySwPwkwwk66Ee......'-:__':'::'::__,_,_,^^^^,,^,;^==\n" +
            "PdE55xaxZxoZ5Zeuee[uu13III3ZG2wPhh49d99hhmggBXKAGbAKKAUAUAAKH888HXKXXXXKKKKKKKKUUAKXAKUGGGKRN8H8RmAHHRm8pkwdO8ol33I1IIf3tune5Yy26A8B89E69ObbOOV2xnto5xSwkwPSwk6PE*.....-'':__'::::::__,,,,,^,=^^,^^^;=;=\n" +
            "69EYYxjxyYx55Z[len[nnt1II3t2h9hddpOO49hdp4Og$XKbGbKUUAAKKKAAXm88HXXXXXXKAAKAKKXAUAKAAAAbUbK008mDDXKXKK$Rd]q48qu3I3u1fII3tu[eZ5jkhUH88dkdkpbpUGpSYn1ZYySwqw6SwPP62'------':_^_:___:__,,,,^,,,^===^==^;;;;\n" +
            "h9SY5xjx5Yx5YZnueenn[313335KOAHD$$B#8DgDU94U#HAbbbAAAAAKKKKXH8mmHHKKKXXAKKAUAAAUUAAAAUbbUAHN$HD$8XXmKK#HAqkVBY6dSZ5tunuuun[ZoYywhGXmRPw494KbpGpVY[1Z5ySkww6SwP66a'---'-:'_,^________^,,,=,^^==;=^===>=>;\n" +
            "99SY5xjYYxjoYZut[en[[II333wX$$mXRP]j55YjkBH4$mKAKAAAAKKXKXXXm88mmHKXKKXXKAUAAAAAKkhpOOUUbAmW#R$DmXm8KUDKhw6U$Z]p4Seo5S4d6wSZ5aaS9UmD#6wd9V8UGGOpau3Z5ySkwq6SwP6hi---'''::_,^____,_,_,,,,=,=^;=>==;==>;>>\n" +
            "hhSY5xyYy5oeZenluen11133fupKOGDGSyY55555aE9gADXKKKKXXXHXmHKm8m8mmXXXHXXXAUUUAAUKUk6Abp466bRMDRR8XH8KXKDXqq6Kq4hpV2e[akhOp]oaxyayhA8DB966P4ORAbGOq[3oYyEqwqPSqP66:-'':''::_^,,,____,,,^^^=^=^>=>=;>;;>;<<\n" +
            "hhS55YjyxxYZellt1uunn[t1}jhGKNKqEajxY5ZZYySh0RmXXXHHXXHm88mm88mXKXKKKKKAAUUUbUAAdVVh9VKGPdD0DDDmXRDBD88Kqk9UVGKUq][[2YwVV5dayw]yEbRR$4hhPVOHRXbpVeIoYyEqSk6Sq666::::::__,,=,,_,,,,,^,==^==>^;=>>;<;>>;<<\n" +
            "hhSY5xayYY5x5Zlu[neett113wOU8BhqSE]yx5ZZZ5x24BD8mmHmmmmmmmmm8mHHXKAKXKKAAAAUUUUAdA94KVVOdpNMR#Rmm$$gRDDKk6kbkX6qkeIeYEhO8X6h2a]229DDRV6h6OGRR8KAV53ZYaEkEq6Sq66Y::::_'__,^=,,,,,,^^=^=====>=<;>>;<;<<<!+\n" +
            "96S55xyYyyyYYelttneoo[111PKG$HPqwSSE]j55ZoooZZEmDDR8888m888m8mmmXXXXXXAAAAbUUUUG49ppOOVOmbWgRDm8Bg08m$8AdV6byYY5I1eoy[ZYPhGK42ES]SmD#O6ddAUA#BDbpw1Z5aEkEq6Sk6h='::__:__,>=,^,,,^===^^=;;=;=><<<><><<<++\n" +
            "h625Yxa]a5yxYZutnooetullIqmANDdPkkkkwEaxY5oeZ5YjSm#RRRRR8mmm88XHXXXKKXKAAUbbbAUbUbGVXGVGAKWBD8#DBMDmR$mAddPGlII}C5]yolluoy55]]]a2EhgRX996UKbX#R8O9nZ5]2kSq6Eq6h,:__:__,,,<^^^^,^^=^=;;=>;>;><+++++<+++!!\n" +
            "hhS55xEayxjZZZut[Z[e[llu1uKDRNX66h6PPqq22ajx5YYYYY]mRDR$RR8mHmHHXXKXKKKKAUUUKUUbGAAXmXGbX$WD#D$M0Dm8#DXX4h951}3xZyY]ol}3t[eo5xj]22wmDm999PDXHKRHKk2Z522kEqPEwkx,______,,^+^^^^^^^==;;>;<;<><<!+++++!+!!!\n" +
            "66S55x]2]y55nnlttuuunnot1IG0ABNO66666PkqwE]a5ene5o[njADD8R8888mHXXAKKKXKKAAKAUbbUKKXXmXD$0MB#D0M$8m##8UVhdd51IZSEbdxa[I3ueZ5xjaSSSq4BXA4Vd8$XmXAOhkZ522PEqP2q6>_____,,,,>+^^^^======>>><><>+<!+++++r+!r!\n" +
            "6PE55y2E]y5onu[ult[un[etluwBAK0g6PqqwqkPkwj[ZxxwkYYawkV##RR88HmHHXKKKKKXXKAKUAUAKKHm8RDBg00$$0MB8HD$DKK5whGo[nY6wSXd]Zu[oYxja]]wwwk9mRA9Vd48gDKmK4[Z5226Eq6EqP^,,_,,,^,=+>^^^===;;>>;>+<<++!+c+!r!!r!crc\n" +
            "6kE5YyS22joneneunltt[oeun[e8KK808kkwqqqqkkS]2a2SaEqa2khm#8Dm88mXHHKXXXXXHHXXKAAUXm8DD$gggM0ggMgRmD$mHAG464KoZZ1[xaaaaueo5y22]a]Swq64HDphOVVAR#B$HPe55E]h2kPEPY,,,,^,,,=>r;====;=>;<<><+++++!!cr!rcrccccc\n" +
            "PPS55aS]yxe[[uun1tt1l[l[[ntOGbURWXPkkkqqk2wqEwqEyPx55wP4m842mmHXXXKXXXXXHHXXXAKXm8D$BBgggMMg00$m8m8AUGhq6VK551Ite[Z5n[oZ5ySw2]]Sqq64X$b4VG99H8R#ROYZ5S2h2k6S6;,,,^^,^^=<+;;===;=><<<<+++!!!rcccccrccc**c\n" +
            "6PS5YaS225en[eY5Yuttt[nn[unuOOKK$NMVPkkPqxaPEwwE2hxj]y/zLcT7hpVmKXKXXXHm8mmXXmm88D##Bgg0MN000$R8RmXUGVq24UKxYEPqyy555Z5YxaESaa]Eqq69d$HdPpU9dXR#bpo5YS262khwq;>;>+<+!!*//****/zss??sLTLLLLTvvvvTTLLLsLLL\n" +
            "9PSZ5xwa2Yon[[5Y5[t1tZ[nntt3fYXKKDBMH<^,^{EPww6w6d5yqEz+><=^+sJFdAXXKHHKmmXm8RRRD$$BBB00MNgMgD88R8Hm894pVGK2jV#Ohdbh]yy]j]E2aa]2wkPh9#m9q4dKUpbGpptZYS26]k6w/****//zzssvTTTTvvv)JJJ7((((|Fii{{ii{{C}}}}f\n" +
            "h6SZ5xSjjjZenZZee[l1utt[nll33u1o9RDHC;;=;Jq6kk9P64Eq4qC7s?z*+<+/nXXXXHmHXmHRR#D$B$$gggMNWNMN$D#gMNN0089kPVHqa4pX8DDK4w]Ey]S2aa]2wkPdpg#9h9VKKXKGbbn5YS]6]q6q/c**z//z?LTvvvvv)J7777J7((7((|ii{{{C}C}}}}fI\n" +
            "q92Z5Y]ayxoon5ZZonu[[nul13t33IIl113}+;=;=*Pp6hdP9V6VVS5uC?c*c*T|IKXHmHmHm8D#D$$BgBgg00MWWN0ggM0RmHHUbmGPd8B4yawSaEqkkwPEj]S]]a]Ew6hV48U94h94HHUAOdYZYw]62k6Y|Jc<_______!___,,^=^;><!c*zLT)7FiCI1l[neojaa\n" +
            "kPkZ5aSEa5e[un55o[1tttonu13I|r*ruuIz>===>/ehOVGp4p4dVVUUKLc**/sL<nUm8mmHm8D#$BBBgg00M0NWQNNNB80B8XUH$hhEwbApj52P6PkS2E]EEE2]a]2wq6d4pHhh4V9OAX8RUOnZYw]h2khwkE3L^--.-.```` `````....---''-'''''''::':?en\n" +
            "kwhyZjE]]Y5[u[nu1111[oe[u1f|f}C|?Jt+====<L3w9UGGbGA49bUXXLcc//sTzJfmHX8mmRRD#$Bggg000MWWWMN$Rd6kk6mUOUROVVGU5n5oYxj]2ES]wES]]ESqk6A9pVhh4VdbAAXRUO[ZYq2hEP6wP6h2wk9qwPqkh62Sw]Yu}(JTs*r!+!<+<><<,^^,_J1t\n" +
            "Pkhk5xyxYnoutttlII1l1t[[uliI1llu{vr+=^=>rv35]hm8DR4PAHmHXT*c*/sLv{}K8Rm8RD#$BBBBgg0MWNGnZZx2Sqqqh668UUUmp9Avett1noYjayj]E2222ESqP4Dhd9kVOdVUGX8DKUuZYq]h2P6wP6h2qk9SkhwkhPE6dV496EPVpSk6hVGS69d9q9G4}rT|\n" +
            "kqwp2x5Zenultt13I}}{{{iFi7{|||F|F|c!>==>r)ItjPRRmR88D8XHUz**+*LTTvZSm8888DD#gggg0MMNNAeo]]Exjh6hhddVRAAOGPhO[u[u55xxYxa2E]22SwkkdK#G9kVGbpUAA8$DXGeZYq2hEkPwPh9]qk9SkhwPhkS64pVd6SPVpSkhhpGS6999EhxYx5oe\n" +
            "PqwVEa5oe[1I}{iF|((((vJJ(F)vJJJJ)s/!>==;;/vJ{j9qvv)(itY5T++*</LTvTCf888mR#B$g0ggM0WWWxZxSEq3Ix94VVVpXRAmXGYHq5ya]yyYx222]2]SqkP9bX8h]hAAKKAKX8mX9eZ5YqEhEkPwkh9]qkdSPhk6hkw64pV4kw6OGSk96pOShd97!^!,+/v)\n" +
            "Pqw96d6kkqESEEE2]]aaayo|u5YY5Z5Zez*!<<==>=<t1[[[[lultt}<^^^>rzsLTvv|m888D#BB0g00MNWWNw5ahGn[IYSUUUUUX8KRKX6hpjSwSES2SwSSSwqk6h4OOXK6npXAmbAm$$Rb[3[5Yq2hEkkwPh9]k6VSPhq6hqwk4pV4PqhOOEkh6ppS64I/cZJzs)Tz\n" +
            "PqwPVSaYZoeenn[nnneee17neeeoeeZZ3/r!!+<>>=;>;>>>>><;>>;=^=><+c/sTvL)aDRDD$Bg000NWNWNgf}nOmP}5qVbXXKX8gXRmm#Y4bSwqqkkkP6P6h4VVd44Gm$G5KKAmK8K8RmH3I[ZYk262Pkq66haq6dSPhq6hqwk4VV4PqhOOSkhhOpShd^(/Zrz>r;^\n" +
            "6kSwO2jZn[l1t3IIIfIIf}f}}}f}}}}}szr!crrr!!!r!rccccrrc!cc*****/z?LTTL(R8DDBggNM0MMHt=so6eet[]hpGGmmm8BMmm#i*(upbAkPk66hh99999hhhdbmBb}RDX88KmK$mu}}[ZxkE6EPkk6h9aq6dSPhwhhww6VpV4qkhGGSk9hOVS9dh*/=,__:_>\n" +
            "6kwwVSyZ[lt13IffffCCCf{C{{CC{{{iTc!rrr!!!!!!!rrcccccr!cc****//z??sLJJ8DD$$B0Rj{>!?*+v!f4GVpGAAXG88DB0NRB)ISq9P]mS6499h6hd9666h99Om$hEm#$RXHX#X5I}}[ZYqS6S6qk6h9aq6dEP9qhhww6dpp4qqhGVSkh6OVShd9,:'=<>+?z\n" +
            "kkqw4PaZut13ff}CCCCi{{{{{{i{iiivsc!!+!!rrcrr!r****cr+!c*/cc///////zzzK#GLJ*L**i/>ic*!zE$D88mHAmm8$gNNMHpzu9d4q5Ewqhh666hhh6kP6h6OKROd$D88HX$Uuff}}n5YkSkw6wq6h9aqh4S69qhhSq6VVVVqqhbVEP96pdwhh9)!?s))|(F\n" +
            "kkqS64ye1I}C{{{iFF||F|||FFF||||Tc**r**cr*ccc*ccc**cc***cc***rc/J|Jsc+<s3*Tc?cvJL>rzs?fj$8mAmXXKHDgNWW0K*L3OOpq]ESS9hh6khPPkkPPkPOUR$gB$B$B893I}}}}n5xqSPE6qq6h9aqhdSP9qh9wk6pV44wk9bdEk96O9wh9q1LT7|((CC\n" +
            "wkqEkS3t}{F(vv?*(JI(JJJJ)J)))J)vF}s*cccc!ccccccc**cc!****//z???zs?ss?czCzzr/?r**!)7iIYbmm8RAKAXm0WNMMBmzL(6bGV69a]69hPPPkkkPPqk6dOADDB0BB#9tI}f}}}nZxPEqw6wq6hhakhdSPhqh9qk6VVV4Sq9GdSkhhOhSh9i/L?vv((7J\n" +
            "*++ccc!!c!/Lsr+cr*zsT7||||ii(|||(JvJTccrr!cr!c*cccc!<r*///z/z?ss?sTTvvLvJ/T+c!/c+vsueERDRXHAKMWWM0g7c<!//s1KXXG2aS6hhPPhkPPPk66694GOGKB82uuII}}}}}e5jkSkwPSk69h]khdw69qhhqkPV444SPdb9SP99bhw9ns!/*L!vLLs\n" +
            "!!c*ccc/rc!cc!!<<<++cz*r/*r+=;+*+;>+cc>vsrJ?>>/r+Ll]2]a]yxaYYYxja]]2EqEy}*+!vs?cILfYZ]m8$RmpsogNC++!!!*?//iUwaoZy2P9h66hPkPqqkP6h99dddOKkYtIIf}f}}e5xkwkw6Skk9h]kddS6hk99wk6444dSPdAdS69hG9wks<!c/c'z//*\n" +
            ")7Tsc<>><r+r!!!>><!c*+><<<sT(}{s<><!s+==>+!*z<>J(+c(Tz/^;zi<c+!>/+</J/>!r<!/c)/ZJfEexAX8HAU2fn}++/!rrrzs/*vhyZe5y]khkkkkPkkkkkPhh6h99dVR4w]of}f}}}e5jkSqS6SPPdh2qd9qh6k9hwkP4VddEPdU4Sh96Ghl>,><,<,,cccc\n" +
            "*>>><+<+/7l[)c/sT)Jv7c+L7u|r*s||}1uicr//L|!>*!c7c+({*sti}csT[?zJTr<f!+)I+z|cz|5f}y]wamOpmDVZZ7!*z?c**czs//L5oneYy]qkkkqqkP6k66Ph6kP666dOg9Sxu}}f}}e5xqwqShEPkd6EqdhwhPk99wPP4VddEk4bVE6hhGT=_;,_,_';_c!!\n" +
            "xYYZ5uC{Jv?i}foE5t7{I}3ltiJJ|Zlv7711u5etT/z)i|*//*T7zj7C1nu7{iS|CC|7TZ}n]qn{f1[yqEqEdUp4bKul|ccTJs*z*czs///{enuYjSwwwqqqkkqkPkk6kqkqkkPhVgPxtlff}}oYyqwqwhSPkd6Ek46k6Pk9hqP6VVddEk4b42(c>=__^^,,,:'>':+<\n" +
            "e1I31lnnn[[lt))vC{}y2e{i33113IJ(|Z[(J|lnYjxeT*T?(cs|3I(yCf5x5|CC2{1it(lola5ui5Sk22whUVhdOX[n!rz7J?s?*/sL//*v5enxyEESqqqqk6P6PkkkqkqSSwqP9GG7{f1fffoYjwwwwhSk6dPEk46khkk9hq66V4d92P4Uhi+,:__,,,___',*r/<=\n" +
            "tf1lY26{7itoe[o5[77|C{tySoif[1CII(7JIY{7|3tl[nI*+vt(}11|{a|5eeu}iny(f1ZluZt32t]h22hVGhk9dHYzc*T()??zzzzz?z/sZZZxa]]EwqkkPPP6PkqwwwSEEE]]2JJ{1t1fffoYxSqwqhEk6dk2k46k9Pk96w6hVVVd2k4Gd)L?*/!__!>;<=r/+*r!\n" +
            "ene5j5Z{(uao{{IZoYYoe(7(C|ie2Z}It11}tC(7to{zz/|{[I|iYL}tu}}k(xZe1)|yeYZ531[x{uEPkdphG6qhhDjc*s)JJsz*szzzzz/*7aoxjj]EqPkkPPkkPkwwSE]]]y3J({311ll3}IZ5ySwSw6EkPdqSPVkkhkPh6wP6OV49]PVUq/s/=L*!+//*//svsv/r\n" +
            "[t13l[otttI{(Cx]3Fin5eZoeu|vv)7ea533If3IiT*cT}vsvtyu|IeJf3lI}a7Z[nifuhn1xe}ZiEEqV9GPO96dkXT*zLJJT?z*zzz/zzz*LY5e5x2]qh9PkkqqkwwS]]]57)(C333IIltt}fe5yqSwwhEP64qwP4qk9kPh6wP6VVVd26VUq?zr!c!/;^''_zz*+L7z\n" +
            "enuZZ5x[fI}}31tIiFnqZC|IexZne[(/s)CoaZi7|vL??szi5CCuoi{f[)3tZiIa|Ye51nPYZl23I]S4pVX6A9k^<Lsczv))vz/*z/z/?z?zzJa[xaYywPkSkSSSEE2]]iL)FCII3ICi|CCI1fZ5awwwwhSk64SwPVqk9kh96w66VV492PVG6Tzc>!^___::_!/r!TFL\n" +
            "2yeou3ulee}7(3IIttu}CieknCCtZ5ollF/*((1Zx{JJLT{I|ulfIj[CleIF3tn(l{122nuYxaI|xahpOVXdX^^<;!>>*LvvLz/**z//z?s?zsYxZ5oY22222]2]2yITv)F{}If}FJ)TT)|{tIZYawwwwhSkhdSw6Vwkhq6h6w66VV4h2Ppbhvvzs//?<!,,=+!+,^!>\n" +
            "Z55jx]w2fC3Cuo1i|C3I31nl{i}yw3CtnZ[f1{sTsT7[Z3}1I17Y}i3ou|3o1CItIJwtE2ynew7oxnpAppA{,_,=^=;^_=)Ls**r*//z//s?z/vy[[Zo5YyajfsTLv77|iCCCii)LszsLvft13ZYaSqSw9Sk64Eq6dwkhqP96w6hVV462POb6)sTvL/c*c*T*<?z7|3L\n" +
            "a5u3t33I5a5fF{ff3ZeI(|C3IIolIi|n2I7}ue[CC)*czI]]1lufiy(ftEt{I[}|C}Iey2oYdI[5IwpGVi?c==!<=,,c,=sL/zrrc***z/z/z**|5ZZZ5{sLT77(F|FiCC}i|7vss??C|vJFiIeYaSqSq9Sk6dSqhVSk6qh9Pw6hpV4P26OU6f(zJsTs//TT)TsJC(IC\n" +
            "3I3tItnx11[a]jjI|FCI3onC(CI3311fiJi2u|7Cu1{i1J/1P1t13IulClY]{1{eT}eZuwxxYfu{{{O9dw==+/>^,,c*+cTs*crrr*/***/TqkSCL?sTvT77|||{{{{FiiF(vL?szCLLL)J(|ClYaSqSq9SkhdEw94Sq6qhhPw6hp4dk2POUk3J=s?7zvF7sJ(vT(z(}\n" +
            "ZeoZZZo[C[FF|1uZy]xu((}3f[xu7JCi(f1fF)tylJvC[l3I)ekItfuFo3J1aj(}o1}Y5ajh}CtlI{C{||{3?[(r7<L/7wys*r!!cc*//zEqw2jZJ77ii|F{i{i{iiiiiF(T?z/)zz?zsLv)fCtYaSqEq9Skhd2w94Ekhqh9Pq69p44P26Gbku)r!7/7JTzJz!{v)!cT\n" +
            "]]Z[u[uu[IifxZ|((f[uYy]Z3FC}CCo177iCiFC|TL7Zy|F{{FL5a{}31FZffuY[|IyuaY9YoeZy555ZltE6V9h9dd9OGaL?c!!!c/*/eI}lo5n3CF|{{C{{iF|F||(|JvLzsvz??szzs|i)FI3Y]SqSqhSkh92qd4269khhkq6dpV4PEhGbqZ7T+zsJ*JT)v?tiL/e}\n" +
            "1tu5y]wka113{||IxuFJF31oy]2Y{J|Ci1[(s|F7iiIiJJtwf7((|El113I{573uxIowyjwZ]SS6kkEP3e]]wwyt}Il3t|?z!rr!*/c2i?zT)7|7F(|iCii|{F||(()7vLszz??//zLiTL)|iCle2Eq2q6wP992w9dEPhkh9kqPdVVVkShObPe7J!<|Tv(7/r|LzvLr^\n" +
            "yZ[tItI3I53u[13tI7|i[53J|}tuojynFvJ)7}{(|(|7{f1}{ISZf732I1lt|Iuiu2aaS6y2{3yYwpV6E]Syaaoe5ZtI1}CCv*rc//2]Jz/zLT))7C77i|FFF||(77)vLsJ*Ls?zzFs|((((i}332EkEkhSP94Eqd4wP9kh9qk6dpVVkwhbUkZJvv!s**z?/i}|v}t?u\n" +
            "]a]a]]jaxllEoCC33113t{(JCZ5|(7Ctnxj5F*?L)C3iJ{I}3tt11jYCJxC1tfFnJewS52k1uet1C}tloySaY]PPwaxZxjy5[t3I7nZCvsz///?LvJLTT)J((7(JJJvTT)sT)szTCCf7JJ(F7f31ESkSk9wkdd2q44SPhkh9qkh4p4VwShGGkj)7f|7z7JzC(/}t/*[(\n" +
            "dPa[nt3I[Zt}C3jEyC(ItI31t3i(71e(LvCu[ZyxF/)FffZuv}1tne1IZw1i|t1fFao]P]kw2y2Syj]Y23Z9[lY6A8pPkE]YZeell3IfvvLzz/zzsL7LzLTv)J))JvJj/svJL/TIIFLsv7{i|I3tuyaEw6S64d2qddEP6k9dwqh4pVVwS9GbquFf7Tt;TT}Lv3?z}F7J\n" +
            "[t1I}}towSneIF|{ClY]yIC3[u[II{7T}5C)J)CCu2a3svJC3517I[nx5I1S]fC}t[5jy6w9h66h6Pk6kEkUelZaeeZS4VPSajYZe33elJ)Lz///zs)/z?sTTLLT)nC/L7(T/TCt{vsv(FFii11lu9k46wwxen3|Zo1JewPq]qhVVVVwE9bUqnvJ3|()C7(JC3iCotLu\n" +
            "utuno5ZY5[loZo5Zl|(Fi{nSSnf{II}{}|v?7efTTT{3eSd2}C335n|unejy[[6k[nEqPhuZahdpOGGUGGUAVkkPxoo[uutYq6S2yxYYj}7)T??///L???s?v|fuIT/T(|Tzs{1IvsT7{{{{{1lu[Vdhw}j2PpOS='<!-!*9[i6VVdpwwdGbkuf}1{{}CICti7lfv(t7\n" +
            "Yo[1t3f1[[lx}ClZo5ZZZC(){I1ZEZFF}I|F|7vTL1[i{IIujEqZ31lZe1[Yx]y1Ikyy6xEZYaq29VVVVVpOUOdu4k2]5Ye[ut1IlZ5ajj[7JJ)Tsz/)*/v*J7v<JT)(|s/Linu(?T|}{i{{|tl[[dpO|hSpmXy]6J<cZn(tq|P4pd}SS9bbqY(|}7}?|}|33Iu}3utI\n" +
            "lluoneY5jx[t{xEuiiuo5ZZou{|7CIuay3(({|CF{77i3xEEE2]]wkennjw]n[t15yy624e5nddqVdpVVGUUpuss7pOh622]y5nl31txya5e(((J)vsLs}Ly[[5}*t{JLzz(le(z)CI}i{iCC1uu[ZVipXw8V2q9xOe|IGYIUEIethaaEhh7|nf((zs**c/LJJ}i3Ifv\n" +
            "]Ejxee13IttltetiilSy{|3eZZZeZ3(iC|Ixx3v({C1[eo5ZeeenZYY5j2oIoYt)]y66dyyl[tPw!bKUGOpIc?sL?*4OVO9qkqw2Yeex2]aZe(|F7JJ)Jl[aTT}niJ}z??J3ev)CfI{(||i}ItuunZt}8Rbau5ESkgK2YXDdHPz3j6[Sjt7FxtInl3}{i}||{{ii{f|)\n" +
            "oeZZjZxaESSZIlI3t[u{FCxS3F|loZnee}JFFC}xyoIfn5Yaxjyxy]22222EEo}nxo6ESPxZ3ww9dOAph2]i/Lv?z/zkVpxAd46SkSyy2jEyZZ|F|ii{}fCJ^c7EtvtLz7}uF{1tC(J7|FC}tt[1tC}5RBatjVS6K0B8OX$gmD9nd9]q6i?/efCui3ff}3{f)7[(t31(\n" +
            "I3CCIf1I1tnZunii{1ftlulCF3jk}(ie5Zonu}(F}1eEPPkqw]2ajx555xYZoYYSxoxdkk1[5w{4VpOhwk]ETLTszz//*dey6S2EkPSEy2aaYe1(F(|iIlnCI(iqC)n/J}n1It1FJJ)(i{}fu[1un5th8#Z3nujyZ000$mg0D$Ui94pwaYel(77n731331I|)Ct|1lC(\n" +
            "C{I}}f3l[ZyxtIfuZef|fft13tl3{|e]I7feono5nt}f1ueooneoZ5xyjaESkSwa[Z]k6a]ek3?y]VV9VdanoLTzs?z/*TyG9wyu[ySS2aaaaxY(Jv)7ij/51}Cy)1/T{nn1lC())J(i{}f3tl3ul3uDRBjaSk6dEY#NW#NNghhYqdpj9we5f77eJ3o[}nu{{}3i3{tC\n" +
            "nuu[n[nlZnnl3]o|C{tfe5uCJf1tI11f)7YEI||[xxyY]2S2Sw]]yxyant}CF(uu[h26hqeq[toKEdGbVP]E]LTsLssz/*I9VV9kEayaS2xa2xxZvLssviSfo[ysxzT}noeI()TT7J|{}1uuC55[layBK0p9Vhd4GXWWQ0NND46t16VVhq|EP5u31[Yo3jl3I1[|{|3u\n" +
            "u3ffC{}3t[eno1ny]EYC|Cl}tYZ3Ji}CfttIiCeP]1C[ea]SSaSPEwq]]yo5eeeuuPw9h25nnva$6VpG4kyXlTLLTL?z/*/Yk2w4h9PqSPaa2Eay|Tz?Tc[jyeJezv{t5o3Jvsv7F|CC3C|{3u7YPqnnHBg8BgWBMWWQ%NQNmnnnnkp49kaEk][IoZ][uynt1ne|lCe1\n" +
            "l[eejaSwhPkE5u{{t[oy]aeCi{}I1ZIJ7{Cfu[1I}}ewdOp9h]x5YxZ[IIC3n5Yt3eSPh]ae}(3KdpOG4Pw]{JLTLLL?////kkPk9hhPwEE2]]EyYTssT]]]S5nzLCIy53JTJJF}ffIZnf3CiCZqqa[j20gMMNNM0N$RHNNBSxleZYV9kxx39keIn5]ZoaZeu55Cotjt\n" +
            "}IIff}3}fI3111CooCFifunx]axu{|FFto}7(fIfnxyxyaayYjyjx5nu313uZ25YPZwVha31ji3UGhpOV9dy1|sLvTLsz///cV96wSqkwqww2]ajynvszz)i1izLC3y53i{C}113fIFiI|1?nE]aEZeYx#$gNNWNBDR#XW8dZh[ykkwaVdUKO4aujY]ax]yjYyalZoyf\n" +
            "xayjjYjx5jxxYol1{(7lxtiF|3n[5xY37iCI[Z[3fttuZ5yjxxxxYZnZ[nenttelkIkhooow5CFbJhpOp2moI7sTTvTLLz//**6dVdh6kkwkESa2]j?sLLszz?JCtnYn1IfC}{{{fu7}{i{yy][enSZxwAAH0WWWNMWW8Hbp4dVObpGGOKS[OVwx2Zx5Z]5jxxaZYx]f\n" +
            "I3}}C33nn5yxyjututufF{Fu5t7FCItexyu|}3Iu[a2Sw]jj5ZZol3}{}{f1tnn1x5quuZwuwfI884ak4U5Y[LTv)JJvLszz//cTVd969Pwkq]y]ajoTTz//?JC1n2Zu{|7J(((e3i)|?}ZEjt3Zalua2OAbHNMWNMDW8kRAVpVUKO8Xbb6XKUdaZ5n(oa1xjZaeYjY1\n" +
            "Cff1t[nYSk6qy5txfC13ll31C{|{ol(){1[jaa]Z[tu5ZZxxZYYZounu[o[eY[nqjoot]w1h91ntR8K4eyajo7((||F|vTLzz//z/*{pPP2wSqqwajYcc*/T(Il5hae}F7vT)Ff{{<fzlSatfieZJ3CY2hhwdXD00ghNN8A$NDMWNMRXmnxUOmmYFFxzuZfZ]texneY5\n" +
            "1l[eZZZZo5onet}}Iy2YI{l1[3ut{(7fZ}FifeZ5jS69d9h96h6w]Z3C||J{[xuqunY]aj9Vnt52wwqS2]yx[3C}fff{|()vs//z//*r4pk2]2w2aj5]zsvi3[x4wYt{(7J7fCi)vJ([]ao?Fet(CZot[2S]6A8#$XkGW0XHR$QWgRPk1bP4ZeuuIL}|3[|1t|3YIteZ\n" +
            "1131t[[[[uuut[[u{iI3[xEY3{flffIiJ7iZY1}}ut55ja2yyajxeeYxjxYPxotulyw]YpdSitYSkPkwS2ayuCItt1tICi|J)Lszzz?z9V9wwwS]jY]uv7{3uYedaZ}|(||31C|=c/nxZ{JF15nCi[tnouxwpOUXXb]2#0WKRWQB6pHqAAUbjYt9l}iiCn{i(iCC33ot\n" +
            "ZYYxxYjxjj5nu1I3xonfCiiI1Z2aeF{II1uuCf13eaSqwqqS]Yuf}C(JJi|[5eZe]PuwdOq2Ct52kP6kwS]]5Cu5555[1fCF|J)L?z?TT4Vh2kwSo|}sJFleeun9yuC77JIiJ7*LTY]t{T[IY55a6I3auna4GbUbp49VA0WPD$]YYZVGXAkp462u{7J7f13f3I3fuutC\n" +
            "i{I333l3ltltueul3[xyYoYoFi{{f3]Y{fluZlZ5oxjxYxZe[3C{F7|}C35ne31]w1PpO2wV31YSkPkPkSS2xox2E2Ex5eu1I{F(Jvv)7S(]a>_`szicF|{|v>+/?*+z)t{Cz(s}aZ[TJluvyqh49f3e[]d2kObpdOVGUHMhmqwU$mmmHyKw2qA8(z|}FJCi{}1uoZtn\n" +
            "2SkqPkqqq6kqE]yZ1wo|i[Yy5ZZZf7||C5kSZutexxjyajjx5olttffI{tZnuy65wkphawpYtlYS6hhhkwSE2a6Pk6PS]yYZeu3Ci|(|va?aqI' 'T(J(||FT,<!zisv*vlJJ*ujn{!{t|)T5SPVh1[jS6]E]dGUAKKAXXAdj3mD8HXGdP]]PkdmP)FI()737Cf|13(C\n" +
            "13I331t3tenoZ5Zyx1C}wSu{fuY5oet}i{}3u5khdd999wS5n}f}C{}I[e3a2q1k9VPaken1ulYS6hh6qkhU6wU$49A#qhpk2jy]5lZ2qq4yZei!>7i373nI(^+cLvr*JC1f*Ywl3c11?|vonjwd43a2xqE3xAAXHm8HmXU6amDDKU4]Vq5]4d2Xj*|}7L(Js|C{fI}F\n" +
            "ZZ5xayaa2yaY[t}I}1l[uIi}3waI7CeeoYYYe31[nZYZoZZnttneYYut3yqq1wkG4q2q[}2It[Y2Ph96k9.X]A_R M H_RaH=ShO9kc6-a6HZjL[[Y qf.STf>cz|J?sz73cZEo}+1I!|(5lu5]6Vln24264KmH8D8D#R8K4]bAAKU6O[wyOG6]xX2*|3vJ7{{CCFiC1\n" +
            "eoe[Ze[[lt3CC}CI}t{I1}ueoe3||xn7{nSaESwwEwESEaYe1CiuZnFxPS1whOpPwqtZekI3Ie52k69`dd'4:K`DX&,b>mjA`PO{Fhqq'E<`h[/xen`1F`= {r>;!<+?L{s[ati+13LF}iIxtoaqhSIaGVOK8DHD$#D##Dm456k9KmKKEdyOKVa2XRvC3JJ||{}iC}{C\n" +
            "YoZe[Z[ex5nonZunn[nZ1|{Ilt31[f|I6wyZ5Z5x22qSSyj5Zt1fIokwnw9pppwaquj]oCfI15Y2k69Vh6f`:8-r`H`?`G``dSh2S6-=!tyYyYs]u1-`-`[,i=!L)z??fsjxoI!Yt*|FivnluZaq94Z9UOd8DD8$$$$$##865]SkUAOyOwj9XmKUSqYT}I7J|I(ICvIC\n" +
            "9Pk6kE2E2xYulf}C1u133tnYet{i}iIZZxZoe5xE9VI1{}1{Yd9y[jShhbbVw2]xYZ6|}3itlYy]q666PkkP6dG$OPSSSq6kwESwEw22xZZn5uu7i7LLICF7TTL|1|J(L[21ustezFI17FanZx]kVb5KAbA8#D#$$B$$$#RmY9X$2VtGbaaamm80mMMi7IFJ}7}||3i}\n" +
            "It131ft311tt1uuY2q52jI}11131o{|1ul1ul1fCICxY6d9d99hhOpAU49qS]4tx]e{f1l{C3oy2qP6P6kqk64p862ajaa]EEEE2EESay5e[n[:``iC!n[3iJr7I7*vJn2luFIl/T[1[}3n5yEwdG8AhBmmRD$BB$$$B$$DRqpDD45hheY9KUB00$NBRvvI|(FJ|F(fi\n" +
            "22]yyxx5e[[f3f}iIII1e]q]y[i{31eYeffiCtkyVV4VppOObAKXbG4d2yShE5lq3C|3tut}fty2qkPPPkk69OOBk2ayajja]]22S]]]Ey5eZeL` 'IkEl)1)<++,'|n2ll(1IL7tuue5fYa2w6p8Nw]dR$R#$B$$BB$$$$8pSXXRSl{iqqBg00#R0BB})Ci7|(7(i}}\n" +
            "]y2xayyESy]xa]xYYYjZ{FIeeZj]y3}33II2VG4hh6dOVUGGbppdqw2whd9nYjq{I{1Cul}t}ux]SqqkPkPhVOKUwaxYnul[Zxyaa]aaaEaj55[_ -YxZe3z>^>,.<Zan3753)Tui[[5jf5Ek6dKMWVkE408$88DD$$B$$$8G9ww9OR$RDPoV2m#DB$D#?zJJ(7|}(IC\n" +
            "kqS]axxYYo[I}FFC3ue1Cuyel{(}1e]ZYaVPhdpG6h9pK69kwwSwkhdpaYYYVt|il}iuoCnfn3e]Swwwkk69bVw5onoYjyxYe[ult[ZYySExYZon` clxCJs:;>`>nxnt{luvvu|{ueZYtjahVm00DE9P0$DD8$D$$BBB$B#UVm88mmmmdhdpmD#8DD8RqvTJ(v|7|({\n" +
            "xnntl[l1tt}{{}tflYwEut1}}{I5I(5qyVhV4kddq6VhOdd9d4ddwtZqoZdel1tI3{fnZ5eu5[oxEwSwqq6dq]j5xy]Swkqkkhk2]xZeeYw2jZnZe-`F5y5[|<_,xYoI(uiTse(u[55YY1[4d$UpOORBmMD$mDB##$B$BB$DAh9OUKUKHOddVAR8mR8RheF7vviiCIfi\n" +
            "oZ5[[u[oYe3tuun5enelte[[[l3(7I2kVGpAGOOOOGVPGbmUp9Zww[ewO5eu}1nttu1Zxyt5jYnZ2SESwqh4pwaxYjy]Swqqqqkk6d4S]j2w]5Zxjr`,Zxvo}(=5Yat{[Jvvt33e5xyYYt56ApVVwOH#$#DD###R##$B$$B$K6ihbbhObUOVbpX88DRm3e5C{||}Iuf1\n" +
            "xx[uuf1t1i{}C{iC}t31uSuIxAKKKXXXHmXXKKKKAOOhXbdVG]wVdS2YYY5[t55YtnuYajuYyjyyZ22ESShGkqSy5nu[Z5Yxy]]2E2SwPd4qSyxx51'^*?/s/>YZj3JxLi(e|}teCI5eyYYyVUUUXH8D#R#8BB#$0MMgBB$$m6k9mUOUAHbpOKbXmD8mu]olrT[L}7(f\n" +
            "xx2]S2wqwwyajenZx5uICIlHm88RDRDRD88888888HmmHXAdOhO922]2ayxYeoe5eY5y]jeja]aZ5x]22q2]xyyaaZu1lllu[eo5Z55Yxx]2q]eltl3r_;<Lf]j[{{[vfJ}uFt1Iu5xltexxx8HAXmD#R#8RggNXRORNg$B$8694RbbUKXXdpAAAUmH6P[[JiF}|F(uC\n" +
            "dhEye11tZZ2qq69hP2ouewm88D$#$##$#DDRDR88R888888mAHK4w]]a2]ayxZaejy5]S2Zy]]]jYnnexexZoeZxxw]Z[l11tu[eeeoZZYYySuCt5I(J'_!/E21Ctos}itl{3lt31yy[nZ5ZYEdA8DD$$RH$ggWGOKOH0BgBRUVpXKObKXGAGV6p4mG9V]n|LL|535au\n";
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
