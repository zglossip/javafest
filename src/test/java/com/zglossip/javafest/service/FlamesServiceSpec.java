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
            "|)I1[C3I1lo5xyxjaayaYY5ayya}JJJLvCC}Ci(zvvL7L?/sc!*c*zzzzz*zvc^+^^czs///*+r>,_:^r+/*zz?ss)J7(u2]2SYe5Ce{((}o5t{(LTJ{|1e[C|((ifvI[i)IZ[ut3IC{}I}i{3[5ulfI|}{iI{iiF31{|N&&%%&%%QWWWWQQQQWWWNNNNMNNMMM0M0g0\n" +
            "|)It[Cf3unxjjyy]]]22ayyxyYy1J7Jvv}C}}iJv/sT*sz*s*+!*/zzzz/*?Lc;>;<>;^+c!='::_,=<!!c//z/LLLTJ7(|F5SZn5{oi77}o5t{(LT7{F1oZ1iFii}L1liJlZ[ut3CC{3I}iC1e5llfI(fiFICFii3I{{Q%&%%%%QWWWWWQQWWWWNWNNNMMMMM00MM0g\n" +
            "|JIt[C}I1eYxxneex]]2]xx5YjYILJJTvCC}F|))z*zzc!czr+c*/z?/*ccLs/<>++>!<;=^^_,,==;>+!rrc//???LTvv))J(neZFe|77Ce5t{(7|3[f3[ot13fttIt1{7u5nu1}C}{}fI{}toZul}3(fiFI{iiF1Iik%&&&%%QQQNWWWQQWWWNNNNNNMM00M00Mg0g\n" +
            "{)3unICt1o5YjZZ5x]]]]xjxyaatJ(vLT{fC{|Jvv<c?!!z/rrcz/zz**//Ts/+;!!+<<<<=^^^==;<<+!rc*/z?sL?z?LTTLvJ[oCe777}e53{u1IC{J/)i)fft}u}Cf{fun[uICC}Cf}}iflZ5ul}I|f{FfCiiitf{Q%%%%%%QQWNWWWQWWWWNNNNNNNMMMMM0MB0B\n" +
            "{)flntf11[ooxZ5ooayyxjay22ylJ7TLTfIivssTsz>!c<z*!c*zss/**/zLs/c+!!!!+><><>>><<+!rc*cc*//*z*/LTTz/zL)uCo77JCn51I{JL*<!*++z*/JtJ){F((i}CfI3I{}ff{{fu5ZulC3(f{iI{iFiuCI%&%%QQQQWNNWWWWWWWNNMNNNNN00000g0$0B\n" +
            "i)Iun}Cf1u5xx55ZY]]2axZYYy][J)vLJi*!;=^=c/r>!;zc!c/zL?//*?zs?/c+<+c*cc!!+<+!+!ccrccrc**cc*zsvL/;!ssL)}eJ7)Ce1iJz=,>>/zccc*Lr/ssLLJ(ii{C{{}C}fI}if[xZuu}I(}{iI{ii{t}D%Q%%QQQWN0WNNWWWWWNNNMMNM00000000BgB\n" +
            "i)fl[f{}3e[Zxyjaa]ayyj5YxaYl))vviJLz**r+=>c+=c*rrc/?z/*c*zsz**c!!!!cccrcr!!rccrr!!c******zsLL/;+//*?sJoJJv{1i7?=,riC{7)777LL/z?css|7(i{{iTFCI3{{InxZulCI|}i{}i{iilfW%QQQQQQWNMWNNNWWWNMMMMM0000000g0gBg$\n" +
            "|)f[nf1t1en5oxxjxyaxaYxyx2x[({CC{srzTT/r!;++;r*rr/szz///z?zz/c!+ccrrcrcc!crc*crccc*****/z?LL*==c/c!z/*7J))CCFv*_[xjyY5yjjelC7L*+++)7(7{i(vsvf}FC3exeulC}|}i{Cii|{t]QQQQQQQWN0NNMNNWWNMMMM00M00gB00B0BBB$\n" +
            "iJIuet1flu[e55xxxyy5YjxxE22n({)zc+rTJz,r!<^<;*cr*zz?////zz/z/r<<!r!!rr!ccccc******/c*c*/zsss<_+**+r/c+rJJJI|J|^oy]SSES2]axYZufJ?>;<?77FFJTTvTiiC1oxo[lCC|CF{}iFFC3WQWWQQWWNM0NNNMNNNN0000g0BgBgggg$g$B$$\n" +
            "F)}[nl3uu1tnenZxeox5Zj]]y]2[|c/{{((7s+_r>=^^,cc*?szz/z///z/**!<+!ccccrc*cc*************/?szc';rr<+cz+>+zJ|{J)>uYjEaa]]jjY5Yon[1ITr^,z{Civs/vsT{Cl5ye[t}CFCFCCi||}ZQQQQWWNWM00NMMMMMM00gg0BggBBBBgB$gD$#$\n" +
            "|Jf[nutulu[neZZjZ5Yjj]]2E2]5IfiF|(JJJ7vvJJ=,;r*/T?//*/**/**/r+++rcccrrr*******/*z*****/zzzc^:+!<<!cr*+=+7IivcTYaa22a]E]2yx5on[u{(/!>>JCJ|TLTs?vIu5yZ[l}C{C|}CF||}gWWQWWWNN0ggMMM0M00ggggggggB$$$g$$B#$D$\n" +
            "(J}[nutun[un[n5xZZ55Y]a]]]n*s/!>==^;+>,;z7)z=!*zzz//**/*cc*rr+++r!**ccc****//*/zz/**/z??z*r'=!>=+rc+**;r/{iv^[|JCenyx5xxx5Yee[tC7?c+<FC)7)czTsTT[Yaent}iC{|}{F||3NWWWNWMN0g$000000g0BBBg$BBB$$##B#$$D#DD\n" +
            "((}[[ltu[nnZnoZyoZYxj]]]2a?+?z/>FIl[en[u3C>!><*/*****c*ccrcr!+!!!c*cc*cc*/*////*z((zJ|vzz/<'><;<cc+r!c;c{F|/zfsz){x53|svfZl}[t13J*!<^7FJ(T*+rs?vCx]o[1f{C{F}{F|(RWWNNMMMM0$ggg00g0BggBBB$B$B#$D$#D##DD8R\n" +
            "((}[nuttuu[eZjjyaxx5ZYyaSl(?*'*{f1lu[nnn[t}(_<!rcc!!cc!!++!++++c*c*cc*c//zz/z///)vv(7vcsFJ>:>>>+c>>^>+<*ii7zv?*z{IYYI[{TvnJ1tC31f?>>=)((|vsr<c?LvZ2o[1fi}i|}{|||0M0MMM00gB$gggggggBBB$$B$$D$##DD#R#RD#8R\n" +
            "7(}[eutul1lu[njj5oZoZxya]i/s;>|{CCfIt[neeeeeo)>>++++++++++++!!!!ccc*cc*////zzzzz))ivs?sL+c-,>><+<^,^<>>!i)|?11t3tqjZuZn|(Lcv}3II3{><>T(J7z/c=>!zTI]e[3}i}{|}{(JpNMM00Mg0g#$BBggBg$B$$$$###D$RDDDDRD8RRm8\n" +
            "(|fn[l331ntu[exj5oooyxjx]{!/->JFFFiFC}3uunoZen[t}!;><<<<++!!++r!ccc*ccc**/z??/zzz??vcsTs/c-^;+=;,:>!<=+/JJFsj2]SSn3tojxxetnn3I31If7,<r7J(z*zc=<<LJ5en3fi}{|f{((g000000gggR$BBBg$B$B#D##R#DDDR88888R888mH\n" +
            "((}n[uII1u[ZoexjxejjYxjyjac^r_^J||(||FiC}I3luneneoZ1s>;><<++++!!cc*cc*c**///zzzzz/cr!rc+c^';,;^:,>++=>*cJ7)eaEy13ItluxE]yYon[ltI}}C?>cLv)7!!/rr<*i}enIfFfi|}{|h00g00gggB#8B$$BB$$$$$DDD8DRR88mm8m88XmHHH\n" +
            "||fnntI}3tnoZxYxjjYx55ZjjxC^/c:,|F{{C{iFiCt5nuuCiu[1CiT;;<<++!!rrc*c*c*cc*/*//z/*cr++<>^,_:==_:^+r;^>c*[{7soY5[|C}cJ3nxYeuul13ICCCi7!</JvJv+,>c!*v5nnII|f{|f{|BgggggBBB$mDBB$$#$D#DDDR88R88m8XmmHmmXHXHX\n" +
            "|i}n[t}31lZ55YYjjxyyxZZ5YZYc/*/='(|iiFi{{iiffIC}tCIuu{|?+<<+++!c!rcccccr!!rrc***!+>==^,__':__:,+!<>cczLLiTcenx]j5elloxZont}}I1I}C{|v!;L))7vT+!==<JZn[fI(IiFfF9BBBBBBgB$RH#$$$$##DDRRR8m8mm8HHXXXXXXXXXXX\n" +
            "||}n[1CfInZ5YZ[[[xyyyY555x5YsL*c=':vFFiFiu1FfC{fI(ut3tAUOXp9(Lv!*cccccr!++!rr++++>;=,,,_:'___=>+<!czsT{Ivz*u[fF{ttnnnen[u1}}113f{{|7J=rJFT/7Jc>=?Tonu}I(Ii(C{#DDD88mmmKUUKKAKAUGGGbGOppOpOpVVVVpppOOOOOO\n" +
            "7FCenlCt1[o55ZennjyyyY5Y5yy]]an/+*<=fR$$#qC||C7{()tIi{kOAXR#Ddk2|c*rc!!c!!r<>>>;===,^,:'-'_:,>;<>,^^^T)|vTcCtv,))(=LF11}l3}It13ICiF7v^>)F(v//zL*s?Ye[}3|I{FCUXXHHKKAAUGppOOVVVpV44V4d999996P6kkqqqwwwwww\n" +
            "i7fe[u311uoo5nneo5xYY5xjy]ya]2]jayaE8#$#$A|T((J|7T|vv}nxwbKKKKp62*c!!!!r!+>;;^=^,^,,__:---___:_<!rr/z+s|J+^)11{C1}{iiC|}lI}331IfC|(v)+z7)(7)!rz/LJ[e[C3|Ii|[6dXRg0gg00gm000gBB$BDD8mXAUOp49PkS2ajYZZel11\n" +
            "Fi|nntffIlZ555[exxaaj5eZZx22UUOUhYaX##$$RUY|vLTTJ)svvLz/cOKKKAGG8YCr!!+r++>=;^^^___::_--.-''!_>sT<*/*=*((szsle1fC3IfII}}}}}33I}{i|J)T*((JvJs/c!>/vYe[{I(Ii(}|F|I}}[Sp8BMNNWWQWWWWWWWWWWNNNMNMM0MMNNM0pZ5\n" +
            "Fi(Fnutu[ZoYjyyx22ajay5Yxjk2ajxxqVX8#$$DHV][3(+<><)F/!+rcpKKKAGpVqE*+<!+<>;=^^^,,,_:-'?5eelI}{{{((|+zzz!LJ/4Zyjy5e[t1tl3}fIIf}C{|v;(J7FTsJvz?c+>czxe[iI(fF|CF((I{iJ}i({i(F}|)v)J|}|vL}i((vsC(7)7{7?)SXp6\n" +
            "i{{L1Zxy]2SwwwwwkwwwwSSSwPSwSSSwShKmR#$D8pwanF!rnq9hqZ)rTKKK8AOpVp5e+<<<<>;=^,___:'--}Zn11Jue{777Jvv<*/czJv<o5n[uult[lII33I}}CiFT*+Lt|T?zvcz!<=>+{on[{f(}iFCF(73{iJ}F7{F(iC|)Tv)|}|vL}i7(Ls}(J7FI7]KwZee\n" +
            "F{C7|J(Fi{}}fffI331111o6x[uunnneZbAH8R$$D$RyyYYYYjxjyySRBBBDHUGOp4V6!<++>;^,____''--'{n3(?ZY2[Czz/z/c+*<cc|(Tt}C}}}f}{}}C{{i|()LLc*|5Tc/!?/+==<?Y]5n[{f(fiiCF(73i|v}F({((iCi)Tv)|{(sL}i7(TT}()]AXe9UG4pb\n" +
            "|{CiTIlnoYxjyaaaa]]a]q2aaaaaaayy6UmmmHm8m8mmmHHHHHXXHHXHmHKXAKUGpVpVj<<<>=,_,:''''':Lx[CrijafJs/!!++>_!!<++y)+TFii|7(777J)J))77)?!;Lt+;<!!c!r<+LSEYeuif(}|i{|((3F|J}F({|7{CFvvv)F{(ssCi77LL}(JObGARB0#MB\n" +
            "|iCCv}teYjya]2EEEESSw2qwwwwwqqqkpXHHXXmmmmHHmXHXXXXXHmXKKKKKAAUbGGpdd<>>==^_<lwDHGK8VmEvsvL?//c?+<>^_'<^42}iJ|I+C()77(((J7|(|(77L!=(f+;^<c!c;rn2SSYn[{}|}|ii|(71{|J}|7{((C{|)TTv{i7sT}i7(LvC7J7gMM$8D8bU\n" +
            "iF{}J(1eya]22EwwwwqkwqqqkkqkqqkVqdVOOpppOpGbbUUAbbUUbbOV99h9ObUAAUAKKu}EX9mOKb6b8dmAKp[>++*rc/r+>^:--_rabyJTvCu1C}7((|F(|||FF((|L*<=_,;<!!!!522SSS5n[i}F}|{i|773i()}|7{(7Ci|vT)vCi7sv}i7|LJC(J)aGp9h96wE\n" +
            "{i{}i}]ySqPh4VbKhd26ddddddddddd4kEGKXXXXHXXXXXKXKKKXHKKKAAUUbbbbOGGGGXbwbUXAGHKKmd9k]u?!!+</*/r+_--':^!UOh(zsv(73I|7(FF|iFiFF{i|)L/>;,_^^=7y2S2SSS5nuFfiC|Ci|7(1i7)CF(i7J{F|vTvv}iJsJ}i7(L7}(7kAOb4Vhh9d\n" +
            "mmXmAAUKXHKXKmXHUUOGKpGKmmHKAUOObV4VGGG8XUKRRKKGppVhh6S]Zenl13}|JTs*rct9kKHXKmUKyAVee[*>>++cjc'-_1m88mXUUbSJvfne1fi(7|((|FFiiF|F77TvvL>c|yy22EEESSZnliCFC|}iFJ(3iJJC|(i77{i|v)))}FJzJ}|77?7}JUA#UUU0ObUU\n" +
            "49pGX8DDRH8XHmmDDRmXKmDRR8GphEwGRDRmG8$$DmmAURR9hmXhpUABDUkRXmmDAmRAdARmHRmAX4AedEfZu/c+r//I2ZSm8UmHHHUGAAV(teZn1Ii(iFiiFiiiii|((|(77Jv<vC3oESEESSZ[liC{C(}||J(f{J7{7|i7(CiF)v)J}FJz)C(7(s(xDgDRgRggXXXX\n" +
            "xxa2S4XAUdhPjlewhh2EEE2SdOVjYOU9kSaeuxGmbObdbmXGd4AXSyka]jGhw3k4h9dU5ddxl9]j993o}i3}3cT)zz5xxXAX4bAAKXAUAAbaZ55u1}{i{i{CFFiiFiF(iii|i|(7sc{lZ2EEEEe[l{C{C(}FFJ|}i)({(|F77{F|vv)J}i)zJC|)uAKMBgRBggND0MRR\n" +
            "oa2]aj55ZYYxy4dVwqStIZqkaaaa]2dh6eYhdPx5[llZpKpbhKG6a2h1wE[u[6wSIwakyhxoj1[xk[CiIIC(zv(JscY5HXUhdbGbKUGOUUK4nZ5ut}}C{{{{F|||Fiii{iiCCC{F7ss9qEaEE2o[tCCCC(}i|J|}F)|i(iF77{||vv)JI|vz7kmgM00gggg00NgKHAR$\n" +
            "le[eZ5aoIx6d][nZenn]h6hwkoCl2Exy2ayqdhEu]9pO6kaS9pPyE]EoEw3kxuZ5qqytEelYxeYqjy(}C()7?Ff7J>uXKUdh4bbUGUUUUbAKEue[1uf}}iiC|(FF|i{CC}II3f3xVhw2jjy2S2ont}CCC|fF|J{fFvii(iF(|C||TvvJIFv?(bpAOVGg$DXbmXKpAAX$\n" +
            "YyaajYoyyj]qhwu3]kq5[ZeooxP44dhZ1[]]22]2kpKXpSVGVy1x62Z9E]j2S1heYZkEx(ZyloSek}f{T7sFs7|JirpKUpddVbbAUUUUUUUKO[nZnuIIi(7Fiii{iCC}3I3n94hw]]]]2xjyE2Zn1{}C{(}F|){C|v{i7i|((CF(vvvJI|vz{bUHmKHUDBNMgUUKmO9U\n" +
            "xYjZ55Y5akqwEEaY52P6ZIe66Een[[Y]OKbh2n1a49dOOV9d25E]u[SkZxOjxY6xeP[Ylyeiox[dnI|)Lvc|r7B$$RmXUpdVGbKKKAUAUUUbbG3onu[tCF}}CIII3IIe9GdhwE]2SPhd96qwyEen1C{C{7}i|J}{|v{i7{(7(C||Tv)7IFT?(VpAGAUXmDg00BB00g#D\n" +
            "ennlu3CI2w]Sxeak6w]2ay5xqqStC]wy5e52awGpGphYeaSa]a9[Ekaox6]oaw2y2dCy}It5ZC9ou5T/TT/wBgB$B$D$0#4pGAKXKAAUAUGGUA4tYYeon[t1l2GpOV9h6kwwwkkdpGbOO4Ejy]e[1}{}C7}i()}{|JCi({F7|C|(vv)(IFL?(4Op4OAXKKKpK8GU96]O\n" +
            "a5ntI3e2S[t3e29ESEjeoShP2a]yxYE6knlkky5awPkhG9qi[j]yya[hSYeyy]YSkwxyFn1Z}SZ[[[zvv6UXDXX8Bg$A#RKGUXHHHKKAAAUGU33G1ea6OGV4hh6PPkqPSqqkhVpbGGSpVVdPP2xu1}{}{7}i7J}{7v}i({(7|{((T))if|s/FSP$hUhUOVVGO9UVkhwq\n" +
            "eooneeoYS5kP6axet3ux6hS]25lx9dwk6EyEP4jtjdVwYj]24Zi2yExPo]da1lhSeyS[n1l(SSjj2qSqP6q]GYhX9ROA9{tOAHmmXKKAAbf{CIlnd9hkkPkqkqkqkkqkkPhpbbAdUUbUGO4d2Syu1C{f{7}i(JI{7)fi7{(7F{|JT))|I|szFYdHm9A9dpU9bHw44mKp\n" +
            "tfi(i{tx2yEPh][5PhhEyY[31n]Pkq6Eoa6VS6dqw64pP3ok66P9fnw2SyE[dwj[E]Z31IJou1ui33n1xatI}i7T|JJI[qUUXmHmXAUV5OOdqSwkqPPkwqqqqPPP6h9hdpObVUUUGUUO6hpdkwxu3}if{(CF77IC7)}|7i((i{|JTvvF}(szin9V$Kd4GVpGUh4KGVhh\n" +
            "teYj2y]]2n]xYaay2h6qY[]96Syxett5PVd49Ewh6h69q2aEq]}nE9]f]axjP2xkxf11}(tIq]1[{Tv|f3}t11eZney2aSSwVKXXAAf3dbUUOV44hw99kPPkP666994VOGdKpGbbUkG6hhh6kS]]I}ifi(}FJ)f{J)CF7F(7{F|JTvvFC(zzindVVmGAAUGUkS64SybY\n" +
            "2Sjn11131nSxC}5kw]y]]aywPhwexh4dax51nZhUpd6x5S6h2aZja]Ziah[PEPShn2u3}3}5e[unYY[Z5ut}u3(T)(}t1[tuo5xjS2SddOGAAAAUO49Gpp449d9d49VVZUp4pGKPwS6ppdhP6E]a3}iCi7CF)JI{)JCi(i(7ii()T)vCC7ssiuw66dGdpGh6692jGaZq\n" +
            "Yya2SSyoC}5Z2kPwSju3t]w]YxY22q9pSnw4ddwwxI1]GV9wana9255un2a}3EwSyY[tt|C77||(|Fi|ifFzojn1ZZn}vv|}1l[eZ]]Zxd4OUAAAUGdAUbOppOpp45wAp9hpApSyqVOVhPPkqayjY7iv|CCuZ5]PeeadZCFi3{(vvvvC}7?z{5Vd]6hdw9hdw]qwoyOx\n" +
            "yyxYYYxx5ZZSY[nnnZa6kkSEe}}YqSawqwhVObheqOGd2ufi|Zayqt[k]l[lfo5u}uui(ffy[1{37TvTTs*/zL)J}1[[[ejax1(F}tutue69dVObAAGVUUbGXm86dAG4PhOU4]jkOGVkwqkwkyxx5LTTuYtirzi(20BBBgAT/y|vLJ){C7z?{qOqw2aqkwaSa]5jdjZP\n" +
            "jjYe5Z[[llYywu}xkkxonneexq69w2Y1t]hhq6wPqh9kalf}fII3CioZZlC35Yyan1t|I)Zn5JJ{v)TTvszzTxGO9TL(|If3tn5j]ayut1[ohhhd4VGOOSOt5YnSAjqdOUUhjohU4w2EqqkSSyxxYnvkTrC+vI{JuLZ62s[2/f2oy(11f7(9652hhUGKAKAOd9Sk]2E4\n" +
            "}}{CiCf}l5yyajYY]kwyItqPE[n[Yex2P6hqYutqhExjenln[uut33fI1I}[jSoY[o|is{u]ZZC{u)?LTvIhXGVGUA9TTv<*J|i}}Iutft1neEkPPhPhqa2xRUwu]V2pAVquSdk]]q996kqEayx5Zoaj<<)nlffi7,+*{c^rcs]x{|{}}YwkI]Sxa2]]jykSwqwSS5yk\n" +
            "2]ww2Ea2ay5ex5kkqyEyxxxSP]liShkZneo5YShkSaZfFFi{CII1lun[[u[no[[}uouJiia5n{q)TTL(Ci3fVOVGUUAAAJZt|}ffi|}ftI11uoahPh6q2x5w]hk{Sd5AdS5a2yaPdddhkqSEx5ax5[y7<;e]5xlte_::^+,_>=zkJ)TC1[Zxhh95haa]]a264wj6axSh\n" +
            "YyxyjE2]a]2]nq22S5eZSkqayyayyEd6tuhSEnooZlueyy5y5x5yxxyoZZYYYyooe3{(F3noejh(1JT)T|1[144GOGUUKK(??({un3If}3133tu64VOVdyV[IZ[o2qO62oYy]PdV496qwEy2e]YuyYe<<,}C(|7J7{,.-=-.=TJ5}Jv|7[3Ie]S5Pau5SuxqS22PSP]E\n" +
            "Y]2EwqS]y5Z5oa5t3f[S6wjSy[n]dkSwEyy2kwZ|3awYZ1ICC1}FfC{3ItonZZZYx|{7(I[55V1=|TTs)itcjpppVOGUAKA[iIC)77F{}F11If316pUbpXYltZdZUVqyno]9VGV9kPSwaw6w]xh[F{55r^_<^,-^'--.`'.-+5555iTvJF1}F3Y2oe35xt5ya5ZPjSZa\n" +
            "Eaa2]axyyyYxl5eSkqSYjuCIuyPq]qEYydhk2SYYoo5xxxx5Ynu131t}{i3ojwxo5[C?F{njowaJvvLsvJ7lEhOOppOGAAAAdvi}}i(F{{}}333tlhVGAh]t[tGO9]aI569dqE2]S]yawSqh6ufIfn5ll_^_'-'',;<<c''!e}Cl{}fCff|zT(n]et3lntu[olt]oxua\n" +
            "u1ttl[lu[tul[ejyq69juyqP6]5Ynu[]9qw]YnY]EyyYn[tluuul[e5eYZZ5yyZjF2i(oooCnwk?9(TLTf+e29GppVVpObUUAK()vJ(|FiCi}}1I3lbOpOGbUbdwy5[5a2ESSqqq2x9Sqkq113YZ5}nuC//r_--.-'--+rzTv)Ts?TssLc}5LTCuIeune3nlul1e[u3E\n" +
            "7)77|{}15xy22yoqjxy]j2EPhEZeSd9Exot1uaSEa55ee[1}3II3IIIuYEkddae|eZexY}1C|]xn!rz*sntuZVdhhhh9VOGAUUUKXevJ|Fi(Iiff1tuUAKAb4qaxe1eakd944d2wdhUOVYf3YwaeZk21{J7iT>>^,:>:'|^:=r?+>;,>!?}[Sl)1a2epZo2[I5Zot1nZ\n" +
            "ajYZnneeene5ZyES21f[]qjaYaxywh9EnEPkEZentC|7J77J((|C3n]w66dqYux{x5[33l7v5ynI{{{}I3tu5]wEEEEq6h4VGAUUUUKHvTiIIfCI3l[3bG4q]5l)C[yq69d9Ewk4V96YI1obkZyhwnoyYf}3(/<==cFs._cr<^.-,<FiyzF)eZxx2OE6]Y6ayPa[2jZe\n" +
            "ue555jyEESw]aja5n]kPE]2eCf5qq2wS]EPh]au3l[oo55eZYZajE]yayaxY5ZY5eIiy(zFIqj[fF|FiCf31owy5ZZ5y2Sq694pbUUbp]vJ|}i{fVAZGhqajoZ5)15S66hw6qP8wUYloa44ZwwaEaaYuet[J??z*zTF7r_-T:'+i1I7}{*T7JJne69k]]jkwSq9yyadS\n" +
            "qq2]]ajajyjjxZxx]Yu1[e[oPqqq2]3[qEjYejenoulu[leZY]SqPh6Sw]n5Z]a3CaFTLf{v]y[}FFiFi}}IuouIfffunZYy2qP6d4V49}h31D0%GUqKP6q6VD8AbA8U4yqwU6GS3eYpdxxVt{()72]ZY3)IiL?TJLvs/!:(!{Cz=!+!rt*{I{/+hb6SPdwkqSaxoey5\n" +
            "en[ul[[[[1I3}{}}uqn{akkj1eu[Zj9hSS]1C|{1l[eneoZZox5yESEqjY5y1{fnCT7}3JwExx[}|(7|iC{iC3LTJ((|ifI1[oYx]qS2wd}ZYk$WWVPSdqw6pg8XOhOGAZw2bm[tjdReapVk[3iT7YeI1(}utLz*ccccrcz7ls>=+czinie1((3r>pa]d46SVP2dq]qq\n" +
            "eenu1111It1[5yE2SyjYY2kSa{129SZZo[[uoaaY5e[eene5yy5Z[uxjat{{aCis){I{YSI2y5[f|(7|i7QrI/0<&'&r0<1r$}7L7iX|W1|!elOYY[@{EQ}V2DKU6dbGb9]Xe}oSma2mPh[xxn3|Tx5I)I|v*!!+><>;>+*)I?//cz(LYC1ss|3urIK6]Vd9qqwwPqSa\n" +
            "nn5Zee5YnZxYxYxxyjYqSayaaj5a6q5uxaxetfFC{}CI}1[ttexxyaY7Ijf(LL7Cf}[Y7y]S2e[fF|(1J(%s*<N<g;%*0rt/D|v({spJMZ11IeOtYx%[Y%oNwbRRKUXUhPw5ly9q2kVGZhyIjo1{((j{?s)!<>!;==;;;>+Fn}}|*c*i((tLrc}5F+ZRxw9khE6PkOkw\n" +
            "J|F|F}f}Il[xxEEwaxy]]y5[ZyqkSk2enunoZnlf7v2awEaw[J7t5t}(7zzv{I3u[e|6E]kyjutIi|((Fii|()s=LFC}}{|iCf}C}CIIuenZnxxhk9Op2Sk9ppO6aPdhO5IaYGyZUP2a9P35eu3iv?n*/?/<;>;==^====<![7c=Ivjs?111+!+_+:'k92PdShS66]kS\n" +
            "]]Eqwq2E2S2]22]Eyaxt33[jSE2E]Z]wwayywkwP]{v)T)vJ()vLz*/v)C3I7eIoI2SSay]Sw5tI{FF||ii|(vT,|33ttt3IIfIIfIf33uoZZ5W&%8kEjPojwAOVmB9e11oqa2pPhY55eynl3Ci)z_?tc,;><==^>==^==;>svc!zx[Zy1i^^__^<:>=VVw6dk9hkhxq\n" +
            "ItIl1t1}}13u1Ilu[[lewk2ZZnt3taSa]22Ivs)((|JLvzss?TTJiCIC()75[l{q]qawxxSyEYu3}{iiFiF(vs*zC1u[5xjYeu1113131f1l[n50%W[uno]bDBRBQRe35a9nadVYk5Ynt2nfi(J*:-vF}v_+^+<>>=^^^==+s7CCJL<=<;Fevf!=>^^>;bUddhhPS62S\n" +
            "q222yxnu1IfFCi1nxyEy]qEjunS66aoCtLT7vzJ|7JLv+7JTvJ|J7JL1eoyv[a6Sjww5ZYoaeao1}{CCii|JJftoenl1I}}f1tee5x5o[3Ctnnonm%0x55]6$R#Qx1Znha2KPPdqSno[nyY1(z_</?)c|,>^<>=;=^,^=^=;/v*crr!c!vv7s!>>+>=+<*VdOh9V49hE\n" +
            "oenYYxYo[Z]yYxZnoZZxyZYYYx]hh2IivsT/sLsLL?vFs?!zL7eCCYoCL[ZxEy5jyYaeutynl[ZeIC}}C{(vTC1l[lt3}Cii{{ii|J)}3tIC3[eulH%Beu4oSh#n[1jqYdVVj]]Znu1uujn|/TTT{s!;^;>>===<;==^=^^=*(k7??(LzzLv?Tc<+><!]onwq66S]xEa\n" +
            "I}3ttu3I1uZeejwhVdP]Y[if*!!!!!!+!+!!!r!!rcc//J7Fz|s?13Ill[n5ye[[ye[utlxu1tt5j1fIfCvF}}I3[YyjYY5o[[lt1t31fC|i{t[t]a2$UbbSXd1tydenVk5yk]yxaje2II557//*r!<==<><,_,:'/,',^^=ri(7<vL//rzvT*L*r!+zVZxyOV54hVjq\n" +
            "J(}tZaayeeI{{|7(FIoxZC!+<>====;==>><>><+>++++++!/!*)CI33I311un1Zltn3}Iet333l[55ZuZueoZnuuC3e5jaayx5ZZZoee[[tCxwyn2hdNgmAfIawyoOSkyxq]jyay1tY5nnn[fJ/+;>==<r=,,-?LcLr_^,^<zTTrcL?*cs/sv(T)!sJT356Op6[]n1x\n";
  }

  private static String getDefaultMkAscii() {
    return "]a2wEy5ja5n5jYyyotu961*+Luu{}lneon3{f[u[uuu[nu[u[e[[ne[uuli!==,_:'-..--:^^,_:-'^^^^==_.` `,<;>+*)7v{J^._v(>-'rJ(*,:=*F1ir,zV89;` `'=c*+ccL||(T!_--....`..`.-:__,,,^^,:=<,_+{a5exxyyYZ64yZ[If[xS25t3is!;;\n" +
            "wqkPwE2]wwYYkSYt[[[5hOkZ|Cflnennn1{FCtnne[ue[eennn[eoeeee[{!==^,:',>!<,:_,_:-'_^^^^^^_.` `^<;>+!T|s?3][c>*;':+Tv/,_<?i[{!,cTT'  ` `.',^,=zIC7?<_--.........'_,^==^^^=;!r='R&&&QnSUpX&&@&&%Q9IuZZee[CLc*z\n" +
            "9h6PkSSE]]2yS]Ya]aaZSAAqxE4kZeoZoI|731eZ[nnnneeZe[eZZ5ZZ5nFr;;=,'.'=<<;^'-''--:^=====_.  `^<+!!cvvc_'rJL+*!::!sL*=^c?v3f/^<TL.`   `.'---'<zLs/>_'--....--.-',^^^^^^>>;==<sv|t3teE699kDMMNND]un}I}FJL/c!!\n" +
            "kPkqwSwkS2]aYaaqhkkkdKm4]PkZ3oZo[I{}}t[n[eeenneZZeneYZYY5eCc;>=^_'--':^^,__,:'_;=====_`  .=<<r!rssc^-'-'^+^'->zr>_=cJJs*>=!)z`    `.''--'=!*c!=_'--....----:,^^=^^==<=,_^r/cT7{f3[one2kk2o{)7Tz/c/LL/c++\n" +
            "kPPkqwwS2yaYyxyYn31ZaG8Dbwy[unZZZ3I1neoZoneZoeeeoooenxjY5ZC+;<=,:-':>r*!;==,_'_=;====_`  .=<>+!rssF(7{w9wOG6l(xqEkY6dYic+>!!=`    `.'''':;!*zc^_'--..--'--'_,^^^^==>>=_-'_=!**/zLL(F)J73t3(!+<<>!zTz/*>_\n" +
            "]2]Zu1I[jeis<<;^<+r/ij6O8Xh6pPuf3un5eeoZ5555555Yx55xyxxxxxi+=<<=_'.:!(7CI(=--',^;;=;;_.  -;+**c/z!|n]Ejti}CekVpR8k3|C)'  `.',c/,.````.-'';+c/!^_'--..----''_,^^^^^=>+='-':^;^,^,=+Tvz==<>=^^__^<c*//c!<=\n" +
            "!=;+!Tl]wkwYJrJ][J(ukko]pmOwV6n3eZ5Y5ZjZe5x55x5e5ZxxYxa]y5i<=>;^_-:_,=:'---+z+=;<>==>_'>r>,>svvL?J<cr+/t1{3)<=*7!.            -_</T*^'--'>ccc<^_'--..--'-''_,^^^^^^;=,-.-;<+;,,^=,,,=>++<>><;--'^c!**<^_\n" +
            "(<+7CZaya]]]Sx[ZYjY2q4pOODBheGdo[ZYYY555xYyx5YjxayyxajyY]xI<=<>=_.-',^><<=,=+^,==^>rz,,;=,_=<r/>_'^c/sc;!:`.`                  .:_,,+c_:'^!*c<^_:'-.---'-::_,^^^^^=>;:',<//;-  `-_^,'.```_csL!:',<+<+=_-\n" +
            "YZnnxwS]xjaEwd6xS4Vhh6669UR$B0RhuYayyjjjjya2yaa]]yyyy]a2ax{+;<>=_--',^;>>=^=_'_,/3T+>_,,__=><!+<:_:_'``                        ``...''=+<:_>!>,_,,:'-'::_-',^^^^^^==;+!+!*>_`   -=LT=`  `:<?zLL/c*!<;>/T\n" +
            "6kq6hPa55YwPkkVhef5qwEEwEqGD$R8Kjy]]yyYx]]a]]]]22]]]y]]2]x{>;<>=_---_,><>>=='?5t}zc/r,_-,+*c=-``` `                             `.....-'^+;':':___'--'::'-:,^=^==^^><*/!<^-     '+s/_`  `.-;zsL/cr+<+cTL\n" +
            "PPPwy[u3Tz7{(F(J317iue5qhqkU8m4qE]2E]aa]]]]2]a]22E2a]]]22]}c+++>_--:,^<+<=_!kPwZ{ss_''=!v/='-'``                                `-'_,<,,<c+^:'--'''--':::.:^==^^^,,;cz/>:`      _!Lc_   ` ``'+*/*!c>>rvs\n" +
            "2]yxl1]wyYYxyjSk]YZuifoj6h6Obd2]ESE222]2y]]a222]22]yaaa22j}!<<<>_'--'=!*rrY62kE}c::+r*!*/+,^^.''``        `.```````           ``.-'==+><>;,':-`..'---'_::.:^=^^^^^^=+c*;'`      ,*/+:   `   ->ccc+>><cL?\n" +
            "jxxeuj]ae3{JcL(3x]u3n5[xkd4VdS5YZZonnZnooZZZZeneZZZZnuo[nu7<<<<<>:-.__'.1pqU4n/^/FsTs<s*>/!<^>^-.--.``...``````           `..-`-'^^>>^,_.'`..````....'_:'-_==^^^,^^=<cz<:``     ,zL+-   ``  -<?z*c+<>/T*\n" +
            "xxx53FJv*>,,;!czsv{{|CI5P9VOq|ssszzzzsLLsLz??*/z/**z?zzzz*rcc!!<=_'-:^/Yhqwk1/ZCr7J=>c^_===;<<><*>+^<=^:':..`....`..--.`.__'^<,,=+!+^;-````````` `.--'''-_^;====^^^><cc=.`    ` ^?z+-       -<**c+<>>cL/\n" +
            "oYjx3J3a2anfi77}IneoZenZ2qkhg%W$bhjs,=r*r!=^,=>>>;>><!r*cc!cr!+<;,':_Ikao2jsffcvv''=-.-.,__=;;;>,:^;>;=>;==::':_^''_,^^_-,/<^;>+!>:.--..           `--'-.-_;>;;=^,,^>!!='-.``  .;cz+:``..` .=/Tz!>>><csz\n" +
            "5Y5[[eYYYZYaSkP6PS]xZxS2]wh48Q&&&&%&&&WKhu7IfT+,>+=_,<><<<rzsz*+;,'^uY1t}s;z!=+'`..` ` '>r^;<=;+z?r==sf/++=<L+ccc>_>+^==*!_-,-`                  ``  -'-',^=;;;=^,^=c*+='.`    `=*s>- ````  _<**+<>>>cs+\n" +
            "x5ZeuuZ5YjSSSyeeoett[j552kdVRNNNNgDRR8mHKbOGqf37J?r>_'_,^=>zs?zc+^cou/Lc,'^,,:```...-'+iJT|7LsL*5yT*T)yf/sz!?v3n?c;;^_,=---` `                     ` `'':,,==;==^,^=cL*,-``    `,*/,.   `   ,c*!+<;;>rT+\n" +
            "yjn1f}}I3I33}|(|||||Ct[nnbBmGm#R8G46SYEUj7lx]a26ShW&&&%W$H6)>^c!=>[ncr/+_^:':'.````.;ta5Pkl[Y3IxGdTsLZnka1uLz37)nf//;,_'.``                     ```. `'__,,==;==^,^+zL!^-``    `_/z_`   `   ,cc<;^=;>cc=\n" +
            "2yoeu1IIfi(7)JJ7(77J|C3tA$UOUG92YDW0#MNWQQ%Mi366ez]B&&@@@@&&&&WR[((*,>^:'-..` `..:cfxyaq962jxkXRD#bt7|ehU9[no)|IL(Z[uCz_-.```                     ````',:,,>;;;==^=>!z+,.       ,c*,`  ``   ,c!>;=^^>/!,\n" +
            "pqSy5[t3IICF77vvJ{3uuexpXOGAbVSZK0$KGbUAbOpG4P4KV}dBWBW%QWQWQ%&&6//,==--:.````'LI)5q9wZt}CC}Y9KHmm8mmRR888RHGUOpPl)fnulnZ}c>-.`                     `.'__,,;;;=^^==;+*>_`      `;c+:   ``   ,!r+<^,>+r+_\n" +
            "8X92wq2ay]aY[[llunyqw6UG6bbpdkkOGGKKKKAUAbUGOVOOVVOh]VUHNQQWQQ%mi>==<,...```.=2ZT|Cf1t5S9dwYltYPGKHHm8RRmUOpPEjYnnYjSpOp4qnuYnr-.``        `  `    ``.':_,,==;=^^^^;rc;'`     ``=c!'    `   ,*r<=^^;</<_\n" +
            "mKRA2nP96qSwwqkP64V9KmpUAAAApdUKAA9yV0gD$0%&%q)ow2|*VNWWWWNWW%Wy==+>;'-..`.'.nhkxuf/c/*7C2GGGV]3aVUKHmXKUp9waSkk94VqE]xaaxo13jPaJ>=_`         `.`..--'',_:,====^^=;>rz/=,-`    ->s!-    ``.:,r!>^^^;!zr'\n" +
            "AXRXKGyEpOdd49ddpXRXOGUAKKAGGKbp9p0%&%Q0ggWWW0u(a21<u0NWWWNWQ%AC====^:..-.` )99Ub4]ICoJ=+<ixaxpV5qX8RRXbkx2k99hh9khhwaf(iCC{IjqhPSI+-         ``.--'--_;>^^^<<r!>=;;+r/v7F)r:``_+?*'`  _Jahwtz+;^,_=+*+'\n" +
            "YoxORMMNNBKUUOpGKUAVGKKKUAXXKbppdmHGVOOGUKAXAbUbXHVIvVMWWWWNWWS+,!+'.-''-.``aDgR8mXG6enjE9GXmR8AdGHDRmU4Om8Vk6ZL!/>!?c<?u5oyw9d9PEEu_``````````.-'':=,_;^_=?J]6aFvoEfcru6pAAKE+,!s+^,J[akVO6]x3)+,_^+*+_\n" +
            "9UDK]2hm0NNBO9OURHHDKdOUAKKAbbbGVVVO4wh4OAOh6kw222Eh44MQWWNNWg[>!,=,'.-'__.-pg#8HXXXXHAAAKXmmXOVOX8$#mAOAX88mbUpPEx25e[a4UAKAGV96Sx(''````    `..--,:'';;==^,zlPXX8X8HApS]qpUKU[*rvJu]q6pUUO66Sa[s<<**<-\n" +
            "00000$A94VGAKKARgBXOOpGpdVpGbOOOh2w4X#DXkEaaaqEkkSEjv(g%WWWNWm{>+==,-`:,=_`!OXAAAKHHm8RRR8mAOVdVA8D#8KUUUXm8mKXXHmXHKKKKmmmHKGdPwj3!.-`...````.-'::_':_^^=>>=^^=;*FaVKR#D8UOpbXRGZ)JCeeyxx5a25ui/!^+*?;-\n" +
            "gggg0MBHKR$RGd$0OppppVppbbUAbpk2tjGP5n133Cs*vL{Z|eqS}+A%WWNNW8ic/=+;:-;r>' JXUp9VGbUKXm88mKbqO$m8D#8HKGGUAX8RD#DDR8RRRR8mXAGO9q2an)^--``````` `.'_=;>=;<=;;=>==^^,=rcLZdUKRRmmKbUmUwPpOOGOpdd9PqE2j1(/,-\n" +
            "0MM000MDKggBggbpppVppOUKHmHXOw[}s/?*z/c/LFSRN%ND|{2kn<SNWWWWWBl<cc!-=,zT+=`+bV6kkhhdAKXXHXAU9w9X8RmXUOGUOOmRRDDDDRRmXKAUbGp4hqyyZ3?'-:``` ``````-_))FJ|in6RRA6[{L+>+c!>,={26VbbdqdkS9OdOR$DX9PS]xe[1{L, \n" +
            "MM000M$V]XWNDmKGppGUKXXHHA4qjCzc**z!(6WQ%QQQNNNMa/x9]sJ#QWNNQ%2^/+r+>*L)/<_.EdqSqwqhVGUUH8U2})v15Ytv*L3y6XXXHXXXKAUbbG4d9h6kqqyYt{+-_'.````````-_._-TaooSVOX8m8DRmXXX4ns=JPVdpGUV42f[aE9OOd62Zult7L*>^- \n" +
            "0000BKdUNMgK99AUGUAKAAAp4wxI//JCfskQ%%QQWWWNWQWNdz[66C+XQQWNNQB[!<ccvz)vvz*.TSkqkqkhdVGUKAAKKKm888R888mmm8mXXKAUbGpV9hh9hh66P]yui!:-_-..```   `'::_sAK94G6Ir!CYdXmDD8mXGpi{jdVGbVwx{I13oyj5eu{(|7s>:.   \n" +
            "000ggNWNDUUHXKAUbGGGV49Se(//TJ1qgQ%%QQQQQWWWWQNNm7fS6u>dWQW0MNWgt^_=;>s?==/,=[Eq6h6P69V4VpOUAAR#$D#RmmHHXmXAbbGGp4d4VdddV96qyYnr_:_,^:..````  ``---.;cLzLJf5SZ/=>z7[EGXXKUOdd9VOVqoJenn[Z5xZeli)s*<'..` \n" +
            "M0NNmGAXX8mmXKXAAbOdq]x3T!!ThM%Q%%%%%&&%%%%%%%QQMj)ZPY+ENNNNg##g0GF,'_***=:,-cywPPPP69hhdPZi7Jv7}3I15pmXAAAAUOpVpVd99d9h6P2YYn!.-:,,,_:'.``` `..`.-___=r++!r/zFIT*!/lk9OUApV9pAXKGdCTii(|3u1}|vzr!,'.`  \n" +
            "N0dGXmHKXHHHmGVV9hk]5T)]AB&&&%%%%%%%%%%%%%%%%QWWWb?lqys[gN0gg$GhKX$%BOxI<,*!_.TE6qPdVp4k}?++><!>>!<<+czuhpVpOpVVV499hhh6wE]x[>:,,<^_>::-`  ` `.-;Jv(i5VKKHm8RD8RK4j{r>)S9VUKAUAAAUU2JvLC|||F7T*+;'.``  .\n" +
            "dGGUKKAbbO49EyjIJz3d$QQQNW%%&%%%%%%%%%%%%%%%%QWWNm)Ck2T3B0gBg0DObXRWWNN0d[/!<zZoyq6hVOOGPu}yOGOdd4dV9j7L{q44VpppdVpdh9hkS2xt>_,^^^^_-:'.`    `````-^;+JI[w6OAAUbObbXHHmbpbK8RHKHUPw2n1ZxYo1t[I(z>,``    \n" +
            "KKXXKAUUp49kY|sx888mA$NWWWQ%%%%%%%%%%QQQWWWQQQWNW$I|E]s1$Ng$$Bg$AbRNWN00ggNQW0g0huy299dV9Y[ZYo5]yx5e{IjOXmmAOp49hh9996kEytT;^,_,^,,=_'--..```.-,,^=^__,;>>+=^+F[Y9OGUGOGAXXKXHKGpw2Sj}tu[u}}tCL/=-```  `\n" +
            "KKKKAAUVdw{/lbXHmHXmKm0NWW%&%QWQQQQQ%%%%%%QQQWWWNg]Ja2f1DM0ggBBgDO8WWNM0MNWWWWW0mp6Pk6Pk9dq5Ffneu}e29GbbUOpVdh66944d6qSus;''::__,,,_:__:-..---'''---.--':____:_Fy2aey9GAAAX8XUV4hh6P2nlZ]ye[utC7c^----':\n" +
            "KKAbOd6j){6UV4bX8Rm88KBWWQ%&%%QQQQQQ%%QQQQQQWWNMMm1Iou7JX0BBg0g$A9XWWNNNNNNNW$6jAdEGAV66kdpVd9h9hhhd44VVVV4d9hhhd96kSo)r!<=::''---'::,^'--.````     ```` ```.``</z/c?CahObUbp969hhPkwC.--''':_____::::::\n" +
            "AGOdkoJ(6KUUO4OKHRg00H#MWW%%%QQQQQQQQQQQ%%&%QWWQQB57YjI18NMMWQQQWN0B#RRD$D88K8hPVq7EKbGOppGAX88mXAUGGpppV44d9d4V9ka1Tz?/s[Iv^..`-:__,,;^-``.-``   ````.``   `` -*zr!rLnkOGOppp4dhSPkq];.`` ``           \n" +
            "phwy|IOApGAH88R$0MMMg8$NWW%%QQQWWWWWWNNN0ggBggMMMNNWWQQQQWNMg$#DDD$Bgg$$#D8m8RBpfan*LEpp4pGUKXHmmHXXKKbbp9h6h6wyIT/z*/s*+T1jtab88ktv_'__-.`` ``  `````   `` ``` ./T/*cfE4GAKAGOV99qwEku-````.-````      \n" +
            "qo|yKmmXKX8Dg0000B$g0000M0g0g$$#DR8mHHm888888HHm88RRD$Bg0MNMNWWWNggMN0#DDR888mDMOYll?/3ES269dVVVpOppVd9P2jZt7s*?TLzcs|Jcz}}El)JiC{1nwGR8q7..````.....`````  ```.``_??!*1]6pbbOpp49PPqwSC...```` `       \n" +
            "|ZAAUGOmDg00g$0bJ!LyA$HHmHHHHXKKAKKUAAKKKKKXKKKKKKKAAmXKKKKAAUUUAKKHRRmHKKKKAAKm0H(J(3iFv1eoYj]2]2]jxILz/zsTvvLLT|1o3]Ou{lyax2PP94OGbUAKR$g0MNUyT,` ` `--''--''---.-!TLv[oaP9d4VpVOdw2wh[,`.`      ```` \n" +
            "UXKA8DBg0BXav>^;>,....lUXUbUAUUUAAUAUAAAKKXXXXXXXXXXXXXHXXXKKKKKKKKUGGUUUAAKKXHXK98qvT)CtxJ++!cc//sszzTvvvvvLJC[qwkg884hhOUbUXmmmHXHXXm8D#D#D$g00MNWQWWQQ0#Oy+_.`-.`.`^s)ty2whhh99dVppVhdk?-..` .-..    \n" +
            "X8$0N$d(csTc_-```      .xUbUUUbbUKKKKAKKKKKKKXXKKXKKKXXUUKKKKHHHXXXXGGGUAAAUAAKXXmKkSaF!Tn]at?rrrrz?szTzLJu]2S6pOXGqG$V]kbAAKKXXXXKAKXm8D$$$$DmXXKAbbOVVOGpVpbXR$Vi_```'/J3ZaS44ppbAAKXHAOG9qAW0}.````  \n" +
            "0#w(^+>,'.              '[KUUUUUUUAUUUAKAAAAAKKXXKAKKAKKKKAAXKKKKAAUUAXKKXXXKKKKXKXK91J{vFuF/3YI}C3)zhhZ]MDKNd[ex6BAS6UXKAAUUUUUbUAKKKAKXXXmXKKKAKUbbVkEa5ouoxaqP6dURXt+--rL|}new9pOGKKKKAG4RNq1hb/`    \n" +
            "._rr^-`                  ^6KUUUUUAKKUAAUUAAAKXKKKAKKKAKKKKKKKKKKKKKKKXXXXXXXXXXXXmXHmKh[v/**JyuLa9kZEXbYSha594]y9KmXKXXKKAUbUUUUAXXXXXKXHHmmHXKXKKAKKKAAGphqyot}}CfnE64Kmpl/,!(Z]PVOAXXKXGG9x}qm5aXs` ` \n" +
            "                         `;kbGGbUUAAAAAUAKKKKKAAAKKXXXXXKKXKKKXXXHHXXXXKKKHHHXXHHmmmmmmXXKG9qqqjZ5kVxxV8UA8mXXXXXXXXHHHXXXXKXKKKKKKAKKXXXXXXXXXHXUKmKKKKKAbGGda1(T(IneYaSw69dV{c}xqPdOO9xYoVw51EAe1Ro^..\n" +
            "                        ```^wkdVOGUAUAAKAKKKKKKKAKKXXXKXXXXHHXXXXXKXXXXXHHHHHHHHHmmmmmmmmHmmmHHHHmHXXXHHHHHXXXXXXXXXXXXXXHHXKHXXXKXXXKKXHHHXXHXKXKKKKKXKKAAAUGphautuC)TvTs?/c;IC{oywyPhyyS4ZZe2hGk}!:'.-\n" +
            "                       `````+6ylkdVGUUAAAKKKKKKXKKKKKXXXHHHXXXHHXXHXXXHHXXXHXHHHHHHXHHHHHXXXXXmmmmHXXXHXXXXXXXXXHHXXXXXXHXAKKKKKKXXHHHHXXXKXXHXXUKKKKKKAUUUUUUUG4kuLcrr>:-``.`.=L(1P6nww]]x5EwOpd6kp9/-.\n" +
            "                            _{A9iI]h4OOGGUUAUXKKKKKKXXXHHHHmHHHHmXXXXHHXXXKXHmXHHXXXXXKXHmmXXXXXKHXXXXXXXXKKXKKAKKKKKKKKKKAAUKXKXXXXXKKAAKKXXXXKAAUAAAKAAUGUUUUUUbd])'`        .,^+*|]dGKKXXXKKAGp4h9bP<\n" +
            "                            `^[9S|TZq9VpUGGUAAAKKKKXXHHHHHmmmmHHHHXXHm8mHHXXHmXXXXXXXXXXXHHXXXXXXXXXXKKKXKKAKKKKKKKKAAUUUAAUAKKKKKXXKKXXXXXXXXKAKKKAUbUUAAUbAUUAbA]+               `cZaq9pbAKKXXXAbO4dpA\n" +
            "                             <nouZvc{Y69VpUAAAKKKXXXXXXHHHXXXHHXXHmmmmmmHXXmHHHXXmXXXXXXXXXXXXXXKKXXXXXXKKKKAAAAKAUUUbUUUUAAAUAKXHXXXXKKKKXXKKKKKKKAAUAAAGUUbGGA6c  `               .!e2SPVGbKXKKKAbppd4\n" +
            "                            ,FtFi|FLLJoS94VGbGAAAKHHKXXXHXXXXXKXXHHmHmHHHHHmHmmHKKXHHXXXXXXKKXXHXKKXKXKAKKKAUAKKKAUbAAAAKAKKKKKKXXKKHXKKKKAKKKKAKKKAAAAAUGGVGGGx'``                  .cuywPdGbAKKKKKUbVV\n" +
            "                        `-1['>/*zFeFss|eEkhdVOGUUAXXKXXXKKKXXHHHmmHHHHHmHHHmHHXXHmmmHHmHmmXHHHXXXKKXXXKAAUUUUUUAAUUKXKXXXXXXXXXXXXXXXXHXKKKKKKAAAAUUUUGbGp44dP7````                    =1j]k9dGGGAAKUbGO\n" +
            "                   ` `|4gggB$x<T3Ze}(J(tYSqP4VpbUAAAAAAAAUAKXKAKXXXXXXHmmHHHXXXHXXXXXHXKXXHmmmmHXHmmHHHXXXKKKKKKKKKKKXKXXXKXXXXKKXXHHHXKKKAAKAAAAUAUGUbGp4hkqi``                       `,i5Ek9pGUUKKAAUb\n" +
            "                 *e2Hgg$$$$#DRG3J1ut|||lnjEk69pObGGbGGGUUbUAKKKKKXHHXH88mmRmmm88mmmm88mmmmm88mHXHHHKAHHKKXXXHXXXXXXXXXXXXKKKKKKKKKKAKKUUKKAAAUUUUpGGGVV49k2a3-                           -7Z]k9dVGUKXXKA\n" +
            "        `  ;e{aDWMgB#RDDDDDRHGqSZ|tI(if[eY]SqPVpp6ylJzc!<=,-...`    ````   ```` `````   ```````````-'_^=>><<!!!r*//zsv7foShOUHXAKAUGGGGGOOGGbGOOGOp4dd9PSyY1,`                            .*[2PhdOGUXKKK\n" +
            "  ` `r6UH$%MBB000gDRR8mmmmmUhqPhO2Z2qwxeu3ir--.`                                                                                ``.,^r{24OVd9d49hdV9hPS2xn|v_                              `!naw69VOAKKK\n" +
            " .cVgMB$gggBXK$g0gR8mHmXAbVhhVh4Vpyqav^;,.``                                                                                      .-.`     ._r1S22]]yjZetJv/'`                               reawPdpGUAA\n" +
            "0gg###D8ADg$$$BBB#mXUGGp466VG6wqk6wv,-```                                                                                                      ` `_sT?*/*TTi^-'.`                             /3xSP9pbGA\n" +
            "X88mmmXGdbR8mDRmKXVhd4q2Y[yqtJL?c^':,>^'..`                                                                                                          `<v|*J*_''_:`                             !lEkhdOGb\n" +
            ":!FtYa2E22d4Vhqyu}3IF*<=:.```  ``.:''.``.-_`                                                                                                          .'^,:':_:::'.`                           `z5w694OG\n" +
            "__::''''----..````````````..````..'<<_''_:--.                                                                                                         `._:::_::::___'`                         `-L[Shd4G\n" +
            ",,____::___''-........``......``-';+_':,^__.                                                                                                           `-___::__:__,___'`                       `<FnawdO\n" +
            "____:::_,^,,:--...........````  .>,.>_=+_  :,-                                                                                                           :,:____::_,,,,,,,`                     `*(lZE6O\n" +
            ",,,,,_::::_:-....```````````     .;L=^^_-                                                                                              `````           '7)/+;=^,,,^^^^^,,,,,'`                  `!}1eyk9\n" +
            "____:''':'':.````   ``````````` `'.                                                                                                                   .![I}}}}CC)v?z!>===^^=_^;'`                `r[5yS6\n" +
            "_,,=,,,,_:'-`       ```````````.-`    `.``                                                                                             ..              .1[tf1n[tl[[e[l133[iJzzc++.               ``.zuYS\n";
  }

  private static String get100MkAscii() {
    return "]2E5anjyou6*Lu}no3fuuun[[[n[ui=,:-.-^,:'^^=. ,;+)vJ.v>'J*:*1rz8; 'c+c|(!-..`..:_,^,=,+aexyZ4ZI[S53s;\n" +
            "96kS]2SY]aSAx4Zoo|3e[nneeeZZ5F;=''<;''-:===. ^+!vc'J+!:s*^?3/<L` `'-'zs>'-..--,^^^>=<vttE9kMNDu}}J/!\n" +
            "kPqw2ayyn1a8byuZZInooeoeoonj5C;=:'>*;=__;==` =>!sF7wwGlxEYdi+!=  `'':!z^'-.--',^^=>_'=*/L()7t(+<!T/>\n" +
            "!;!lwwJJ[(kopOVne55jex555xYayi=;_:,:--z=<=>'r,sv?<r/13<*!      -<T^-'cc^'-.--',^^^=--<;,=,=+<>;-^!*^\n" +
            "YnxSxaw6SVh69RBRuayjjaya]yyaa{;>_-,;>^__/T>,_=<+::'`            `.'=<_!,,:-:_'^^^=;!!>` -L= `<zLc!;/\n" +
            "PPyuT7((37u5hk84E2]a]]]]22]]2}++_-,<<_kw{s'=v=-`                `',,<+:-''-:::=^^,c/:   _L_ ``'**c>v\n" +
            "jxu]e{c(xun[k4d5ZonnoZZnZZnon7<<>-_'1q4//sss>!^^.-`..```     `.`'^>,.`.``.._'_=^,^<z:`  ,L- ` -?*+>T\n" +
            "oj332ni7IeZn2kgWbj,rr=,>>><rc!r+;'_kojfcv'--,_;;,^>=;=::^',^-/^>!:-.      -'._>;^,>!'.` ;z:`. =T!><s\n" +
            "xZuZYSSeot[52dRNNDRmKOq3Jr__^>sz+cuL,^,``.-+J|LL5TTy/z?3?;^,--             ':,==^^c*-`  ,/. ` ,*+;>T\n" +
            "2ouIf()J(7|3AUU9YW#NQ%i6e]&@@&&W[(,^'.``.cxa92xXDb7eU[o|LZuz-``          ``':,;;==!+.   ,*` ` ,!;^>!\n" +
            "89w2ya[luywU6bdkGKKAAUOOVO]UNQQ%i=<..`.2TC159wlYGHmRmOPjnYSO4nYr.`     `  `'_,==^^r;`  `=!  ` ,r=^<<\n" +
            "ARKypd4dpROUKAGb90&QgWWua1uNWNQA==^.-`)9b]CJ+iap5XRXk29h9hwfiCIqPI-    `.--_>^<r>;+/7):`+*` Jht+^_++\n" +
            "9D]h0NOORHKOAKbbVV4hOO6w2E4MWNW[!='-_.p#HXXAAXmOO8#AA8mUPx5[4AAV6x'``  `.-:';=,lX88ASqUU*vuqpU6S[<*<\n" +
            "gg0BK$G$OpppbUbktG513sv{|q}AWNWi/+:;> XpVbKm8Kq$8#HGUXR#D8RRmAOqa)-`````'=>;=;>=^=cZURmKUUPOGpdPEj(,\n" +
            "M00$]WDKpGKXH4jz*z(W%QNNax]JQNQ2/r>L/_EqqqVUHU}v5t*36XHXKUb496qyt+_.````__ToSO88RXXn=PdGV2[EOd2utL>-\n" +
            "00gWDUXAbGV9e/T1g%QQQWWNmf6>WWMWt_;s=/=E666VVOAR$#mHXXbGpdVdV6yn__^.`` `--;LLfS/>7EXKOdVVoenZxeis<.`\n" +
            "NdXHXHmV9k5)A&&%%%%%%%%WW?qsg0gGK$Bx<*_T6PV4}+>!><+zhVOVV9hhw][:,^>:`  .;viVKmR8Kjr)9UAAAUJL||7*;.` \n" +
            "KXKUp9Ys88ANWQ%%%%%QWWQWWIEs$g$gARW0gNWghy9d9[Y5y5{jXmO4h99kyT^_^,_-.``-,=_,>+^FYOUOAXXKp2jt[}tL=`` \n" +
            "KAO6)6Vb8m8BW%%QQQ%QQQWNM1o7XBggAXWNNNW6AEA6kpdhhh4VV49hd6S)!=:'-':^-.``  `` `.`//?aOUp6hPw.-':__:::\n" +
            "pw|OpA8R0Mg$W%QQWWWN0ggMMNWQQNg#D$g$#88BfnLp4GKHmXKbphhwI/*s+1tb8t__-` ` ``  ```.T*f4AAO9qEu``.``   \n" +
            "|AUOD0g0JLAHmHHKAKAKKKKKKKAXKKAUAKRmKKAK0((iveY]]]xL/svLT13O{yxP9ObARgMUT```-'-'-.!L[a94pOww[``   ``\n" +
            "X$NdcT_``   xbUbUKKKKKKXKKKXUKKHXXGGAAAKXKSFT]trrzsTLu26OGGVkAKXXKKmD$$mXAbVOppX$i``/3a4pbAXAGqW}`` \n" +
            ".r^`         6UUUKUAUAKKKKKKKKKKKKKXXXXXXXmhv*JuakEbSa9]9mKXKUUUAXXKHmHKKAKAGhyt}fE4ml,(]VAXXGxq5X``\n" +
            "            ``wdOUUAAKKKAKXKXXHXXKXXHHHHHmmmmmmHHHXHHHXXXXXXXHKXXXXKHHXXXKKXKAUpatCTT?cI{yyhy4Z2G}:.\n" +
            "              _Ai]4OGUUKKKXXHHHHmXXHXKHXHXXKHmXXKXXXXKXKKKKKKAUXXXXKAKXXAUAKAGUUUd)`    ,+|dKXXKG49P\n" +
            "               nuv{6VUAKKXXXHXXHXmmmHXHHXXXXXXXXKXXXKKAAKUUUUAAAXXXKKXKKKKAAAUbG6 `       .eSVbXKApd\n" +
            "            `1'/zes|EhVGUXKXKKXHmHHHHHHXHmHmmXHXXKXKAUUUAUXXXXXXXXXHKKKAAUUGG4d7``          1]9GGAUG\n" +
            "         eHg$$DGJu||nE6pbGGGUUKKKHX8mRm8mm8mmm8HHHAHKXHXXXXXXKKKKKKUKAAUUGGV923              7]9VUXK\n" +
            " ``6H%B00DRmmmhPOZqxui-.                                        `.^{4V94hVhSx|_               !a6VAK\n" +
            "0g#DAg$BBmUG46Gwkw,``                                                    _T**T^'`              /xPpG\n" +
            ":FY22dVqu3F<:`` `:'`._                                                     .^::::.              zw9O\n" +
            ",__:__'....`...`-;_:^_                                                      -_:_:___`           `Fad\n" +
            ",,,::_-..`````   ;=^-                                               ``      7/;^,^^^,,'         `}ek\n" +
            "_,,,_'`   ``````-  ``                                               .       1t1[l[[13izc+        `zY\n";
  }

  private static String get100Height150WidthMkAscii() {
    return "]]SEjYaoZjyyo3S6i+Le}}ueou{f[[u[un[[[e[nn[utc=^_:'..-'^,_:-,^^^=:` `<;+r)7FJ_.vv:'z(*_,*}}r^28y- `,crccv|(?,--...`..-:_,,,=,:>,,Lae5xaxZV2Z3I[yE51{s+;\n" +
            "96PkSS]]ySxy]a5SK6xq4ZeZoCJ3l5[nnneoe[ZZZo5l*;;^'-,<>='''--^===='` .<+!!vT,'z)+/=:*L*,+?71/^sL.   .'--'cLsc_'-....-.',^^^^>>;=<s(t3nE6dk$MNNOu[I}(T/c!\n" +
            "kPkqqw2yYyyxnfnaARb25un5Zf3neZonZoneooonyY5nr;>^:-:>cc;=,_'^;=^=:  -<>!!sL|719wbpl{wEjwde?+>!=    `''':>cz!,'-..-'--:,^^^=>>^'',>**zLTi))ItIz+<>!Ts/r^\n" +
            "!=+!7Ywk]J!j[vIkSjp86Vw1e5Y555eYY5Yo55xYj]yn+=>=_-_,^'--=z<;<>=>__r;^sT)?vrr<I1C{<=v!`        `'<zz^'.'!cc>,'-..---'_,^^^,;=_`-;<;,^=,,=>+<><;--^rc*>_\n" +
            "Yonxw2xyEw4jSV9h669X#B0Oujayjjjaayy]]yyy]2aZr;<=_-',^;>^=_:_/f!>,,_,;<!+:__' `                  `..'_<<:;!;_,_'-':_'_^^^^^=;!+!c^`  -<T=` `,!zLzcc<;<s\n" +
            "P6kyuuTsi(iv3I|ue2hwd8XPE2E]a]]]]]]]2E]]]22xz+++_-:,=<<,>kPj{s^'_>vr:--`                        `-_,=,<r;:'-''--'::-^==^^,^cz!:`    _*/_  ` `'r/*r<>*T\n" +
            "jYZuy2ef|cJ}xy3nZZk4Vd]YZZnnZeoZZZenZZZn[nn3+<<<>:-_:-1V44I,/7Ls!)>*+^>'...```..````    `   ..-.'^;>,,.-`.```...'_':^=^^,^=<*/:`` ` ,sc-  `` -!z*c<>zz\n" +
            "oxy3JZ2j3iJ{IeeZ[e2kqg%0bw|,>*r+,,>>>;><!*crcrr<;_'_nwo]Ff|sv-;-.-,_=;>;,_;>;>;=_:'_^'_,^_->!^;<!=---.        `---.:=>;=^,,>r+'-.` `;/r: ..``=?s!>><*?\n" +
            "xZeu[5YawS5eoe3[yZ2P9RNNN$RRmHKGGqf}J/<_:,^=*s?*+^)u*s,_,,'``..-'vJviL??5o*T{t/T!?)Z?<=^_=--` `               ` .':,^=;=^,=cL>-`   `,/=.  `  ,*r+>;>*c\n" +
            "2Yeu1IfF7)JJ(7J|}1A#OUOwYMN#NNQQWioheTK&@@@@&&&B[is,>_'-.` .._ixyE9kyxpDDD57CqUw1ov3L[euF=-.``                ``._:_;;;==^;!/;.     ,*^` ``  ,*>;=^>/=\n" +
            "8AEwway2j[ulueSwhU6UGdqVGUKKAUAbbOpOVp4]pANQWQQ0i+=<:..``.?yT|C1ux9hjlnwGXHm8RmGOP]xnoxSOO4S[Y3^.``     `  `   `.:_,===^^^=rr,`    `=r_   `  ,/+=^=</^\n" +
            "AmXK4apO949dpmHOUAKKbGXO98Q&%NggWWm(aaLuNWWWWQ%n==^^'.-``)VGbh[C[!++eaqO5h8R8Akxq99h96hwy(iC{I]hP2z-       `.-'-.=>_^<+r>^>+rL7|?:`'+z,``-JEPtc>^_^+*,\n" +
            "9XR]SG0W0OVGRKRK9bAKKbUGVVO4wdOA96qE22k4UQWWNWR_!,='.-_--pg8HXXXXAAKHmUpOX##XGAm8mGGP]S5[x4UKAOd6EI'.```   `.._:'^;=^,vSXAm8HUS29UAy*zTu26pbb6P][!<**_\n" +
            "gBg0MRKDDGKMOpppVpbbUb4wtdh5[13F*vvu|jw}tQWWNWp^/;!:'+>.cXUdVGUKH88HUqD88#RHAOUA8RD#D8RRRRmXbOhSau<-.``````.',;>=<=>=>==^_+cJqUXDmHbUHkPpOGOVd9kE][(r'\n" +
            "M000M8]RQDHGpOGKHHHUhj(**/!(bQ%QQNNWask]?GQWNQW,/+/>zT/;.E9Sqw6VbUHmh}Lf55(*Jx6HXHXXKAbbOd9hkqSjt7__-.`````-_-_TxnSdK8mRRHXXh{=}pdObVh1[ahOp92nltT/>, \n" +
            "0M0gNWDGHXAUbGGVVqe)*T7ygQ%QQQQWWWWNmTY6TaWQMMNgt=_;<T=<;=Zq6hP6d4VpUAH#$DRmHHXmKbbGpdVVd4Vhqy5|__,^'.```  `--.;/?L(nSt;>ztEUXKUdd9OV2|enuZYYet7s!_.. \n" +
            "NDOXmXXHHmpV9625L5A0&&%%%%%%%%%%%%QWWq|qC}gMggDkKXQB9u<>c_'x6qdVp9}/+>+<>!<+*fhOVOVVVd9h9hw2y[,_,+,>''`   `.;sJiaAKH8R8DKkfr+39pKAUAAUPJL{||F7?!;-`` `\n" +
            "KKXKAAp9hYz188mAgWWW%%%%%%%%QQWWQQQWWA|Efi$M$$BBAAgWN0g0WWgWhoa99d9neY[]yYZ{1dX8XOVdh999PSyI<^,_^,^_--..``._,==__=>+;^zlYVGUGOAXKXXbpSEjf[[1Cti?=.`` `\n" +
            "KKUO9k)lbVpK8R88XNWQ&%%QQQ%%%QQQQWNNMOfo}sXgBg0BA9NWNNNNNWDYAPGAdhk4pdhhhhd44VV4d9hhd6kSlc!>_:''-'::^:-.````   ``` ``.`^/sc?1wObbp69h6PwL--'':____::::\n" +
            "ph2|eUpGX88#0MMgmNWQ%QQWWWWWNN0gBg0MMNWWQQQWMg#DDDBgB$#Rm8RKfazLPp4OUKHmmXXKAUphhh6]Izz*z/+7YtqX8a(_:,-`` `  ````  ` `` .v/*/54bKAGV9hwES_```..``     \n" +
            "|SHUOKDg0g$DJ*[ADHmHHHXKAKUAAKKKXKKKKKAAXKKKAAUUAKXRRHKKKAAH0h7(C}vuoYa2]2yx{s/?LvvLT{e39y{[]x2P94GbAKR$0MgqT'`` .-''-'--'.!Tv[Yq94VppVw]6['``    ````\n" +
            "XRgNR5cL/_.``     xXUUUbUKKKAKKKKKXXKKKKXAUKKKmmXXXGGUAAUAAXXmhSY?TZytcrrczsz?L|52wVOA6G#2kUAKKXXHAKX8D$$$RHXKGbOVOOVpbR$P=``./|na64pOUAKHApGq8W} ``  \n" +
            ".=c^.`             cAUUUUKKUAAUAAKXKKAKKAKKKKKKKKAKKXXXXXXXXXmXmU2v/*Ja)ah5EHaS6e9hj9XHKXXKAUUUUAXXXXKHmmHXXKKAKKAGVkye}}{uEhOmPT,z[]hOAXKXpUx}K5bt`  \n" +
            "                  ``.wkVObAUAAAKKKKKAAXXKXXXmHXXXXXXXXHHHHHHHmmmmmmmmmHHHmXXXHHHXXXXXXXXXXXHXKHXXXXXKXHHXXHKXKKKXKKAAUOdat[CvTTs/c/I{YSyh2yS9Z[6G5/:--\n" +
            "                     _eGio64OGGUUUKKKKKXXHHHmHXmmXXXHHXXKHmHHXHXXKHmHXXXKHXXXXXXKXKAKKKKKKKAAUKKXXXXKAAKKXXKAAAAAAAUUUUUUGw).`     `,;*|wpKKXXKKGV99G)\n" +
            "                      sZu}+{ahVOAAAKKXXXXHHXHXHXXHmmmmHXmHHXXmXXXXXXXXXXKKXXXXKKKAAAKUAUUUUAAAUAXHXXXKKKXKKKKKAAAAAbUbGA6,``           .T]ShObKKKKUpVd\n" +
            "                  `,Y'!*ztCsLuEk9VOUUAXKXXKKXXHHmmHHHmHXmHHXHmmHHHmHXHXXXXXXKKAUUUUUAbKXKXXXXXXXXXXXXXKKKKKAAAUUUbGV4d]-``               '1yS9VGGAKUGO\n" +
            "             7]Hg$$$$DR5Jlu|(1nak64pbGbGGUUbAKKXKXmXHRm8RmmRmmm8mHmmm8HXHHKAHXKXXHXXXKXXXXXKKKKKKKAKKUKKAAUUUpGGV49S]3`                    '1]PdVbKXXK\n" +
            "   `JUH0NBg00BDR8mmmXhk6Ox]qEeu3z--`                                                            `.'^Lj4Od94dh4dhk2xf(_                      `!5S6dOAKK\n" +
            "00$#D#A$$$BBBDXUOO4P9GkqkPi,.``                                                                            ` .<T***L{^--`                     .ixq9pbA\n" +
            ":/3Y]E2SdVP]u}3Fc=:.`` ``-''.`.'.                                                                               `-^_':_::'`                     z]69VG\n" +
            ",____:__:'-......`.....`-,!_',^_-                                                                                 -__:__:_,___`                 `staqO\n" +
            ",,,,::::_-...````````    -v=^_-                                                                       ```        .7L!;^,,,^^^,,,,'              `J1e]h\n" +
            "_,=,,,_''`     `````````-    ``                                                                      `.          `1tf1ntl[n[l33uvz/!+`           ``ze]\n";
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
