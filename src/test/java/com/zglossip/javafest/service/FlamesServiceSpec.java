package com.zglossip.javafest.service;

import com.zglossip.javafest.domain.AsciiImage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
public class FlamesServiceSpec {

  FlamesService flamesService = new FlamesService();

  @Test
  public void testPrintDefaultPicture() {
    //Given test data
    final Integer width = null;
    final Integer height = null;

    //When
    final AsciiImage result = flamesService.getMkAscii(width, height);

    //Then
    assert result.getImage().equals(getDefaultMkAscii());
    assert result.getWidth() == FlamesService.DEFAULT_SIZE;
  }

  @Test
  public void testPrintCustomPicture() {
    //Given test data
    final Integer width = null;
    final Integer height = null;

    //When
    final AsciiImage result = flamesService.getCustomAscii("./src/test/resources/good_for_her.jpg", width, height);

    //Then
    assert result.getImage().equals(getDefaultAltAscii());
    assert result.getWidth() == FlamesService.DEFAULT_SIZE;
  }

  @Test
  public void testPrintPicture() {
    //Given test data
    final Integer width = null;
    final Integer height = null;

    //When
    final AsciiImage result = FlamesService.getAsciiStringFromImage(width, height, getTestImage());

    //Then
    assert result.getImage().equals(getDefaultMkAscii());
    assert result.getWidth() == FlamesService.DEFAULT_SIZE;
  }

  @Test
  public void testPrintPictureWithWidth() {
    //Given test data
    final Integer width = 100;
    final Integer height = null;

    //When
    final AsciiImage result = FlamesService.getAsciiStringFromImage(width, height, getTestImage());

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
    final AsciiImage result = FlamesService.getAsciiStringFromImage(width, height, getTestImage());

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
    final AsciiImage result = FlamesService.getAsciiStringFromImage(width, height, getTestImage());

    //Then
    assert result.getImage().equals(get100Height150WidthMkAscii());
    assert result.getWidth() == width;

  }

  @Test
  public void testGetSmallerThanWidthFooter() {
    //Given
    final Integer width = 10;

    //When
    final String result = flamesService.getFooter(width);

    //Then
    assert result.equals(getNoWidthFooter());
  }

  @Test
  public void testGetFooterWithWidth() {
    //Given
    final Integer width = 500;

    //When
    final String result = flamesService.getFooter(width);

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
