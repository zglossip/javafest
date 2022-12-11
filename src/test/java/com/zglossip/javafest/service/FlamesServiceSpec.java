package com.zglossip.javafest.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FlamesServiceSpec {

  FlamesService flamesService = new FlamesService();

  @Test
  public void testPrintPicture() {
    //Given test data
    final Integer width = null;
    final Integer height = null;

    //When
    final String result = flamesService.getMkAscii(width, height);

    //Then
    assert result.equals(getDefaultMkAscii());
  }

  @Test
  public void testPrintPictureWithWidth() {
    //Given test data
    final Integer width = 100;
    final Integer height = null;

    //When
    final String result = flamesService.getMkAscii(width, height);

    //Then
    assert result.equals(get100WidthMkAscii());
  }

  @Test
  public void testPrintPictureWithHeight() {
    //Given test data
    final Integer width = null;
    final Integer height = 100;

    //When
    final String result = flamesService.getMkAscii(width, height);

    //Then
    assert result.equals(get100HeightMkAscii());
  }

  @Test
  public void testPrintPictureWithHeightAndWidth() {
    //Given test data
    final Integer width = 150;
    final Integer height = 100;

    //When
    final String result = flamesService.getMkAscii(width, height);

    //Then
    assert result.equals(get100Height150WidthMkAscii());

  }

  @Test
  public void testGetDefaultFooter() {
    //Given
    final Integer width = null;

    //When
    final String result = flamesService.getFooter(width);

    //Then
    assert result.equals(getDefaultFooter());
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

  private static String get100WidthMkAscii() {
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

  private static String get100HeightMkAscii() {
    return "]a2wEy5ja5n5jYyyotu961*+Luu{}lneon3{f[u[uuu[nu[u[e[[ne[uuli!==,_:'-..--:^^,_:-'^^^^==_.` `,<;>+*)7v{J^._v(>-'rJ(*,:=*F1ir,zV89;` `'=c*+ccL||(T!_--....`..`.-:__,,,^^,:=<,_+{a5exxyyYZ64yZ[If[xS25t3is!;;\n" +
            "9h6PkSSE]]2yS]Ya]aaZSAAqxE4kZeoZoI|731eZ[nnnneeZe[eZZ5ZZ5nFr;;=,'.'=<<;^'-''--:^=====_.  `^<+!!cvvc_'rJL+*!::!sL*=^c?v3f/^<TL.`   `.'---'<zLs/>_'--....--.-',^^^^^^>>;==<sv|t3teE699kDMMNND]un}I}FJL/c!!\n" +
            "kPPkqwwS2yaYyxyYn31ZaG8Dbwy[unZZZ3I1neoZoneZoeeeoooenxjY5ZC+;<=,:-':>r*!;==,_'_=;====_`  .=<>+!rssF(7{w9wOG6l(xqEkY6dYic+>!!=`    `.'''':;!*zc^_'--..--'--'_,^^^^==>>=_-'_=!**/zLL(F)J73t3(!+<<>!zTz/*>_\n" +
            "!=;+!Tl]wkwYJrJ][J(ukko]pmOwV6n3eZ5Y5ZjZe5x55x5e5ZxxYxa]y5i<=>;^_-:_,=:'---+z+=;<>==>_'>r>,>svvL?J<cr+/t1{3)<=*7!.            -_</T*^'--'>ccc<^_'--..--'-''_,^^^^^^;=,-.-;<+;,,^=,,,=>++<>><;--'^c!**<^_\n" +
            "YZnnxwS]xjaEwd6xS4Vhh6669UR$B0RhuYayyjjjjya2yaa]]yyyy]a2ax{+;<>=_--',^;>>=^=_'_,/3T+>_,,__=><!+<:_:_'``                        ``...''=+<:_>!>,_,,:'-'::_-',^^^^^^==;+!+!*>_`   -=LT=`  `:<?zLL/c*!<;>/T\n" +
            "PPPwy[u3Tz7{(F(J317iue5qhqkU8m4qE]2E]aa]]]]2]a]22E2a]]]22]}c+++>_--:,^<+<=_!kPwZ{ss_''=!v/='-'``                                `-'_,<,,<c+^:'--'''--':::.:^==^^^,,;cz/>:`      _!Lc_   ` ``'+*/*!c>>rvs\n" +
            "jxxeuj]ae3{JcL(3x]u3n5[xkd4VdS5YZZonnZnooZZZZeneZZZZnuo[nu7<<<<<>:-.__'.1pqU4n/^/FsTs<s*>/!<^>^-.--.``...``````           `..-`-'^^>>^,_.'`..````....'_:'-_==^^^,^^=<cz<:``     ,zL+-   ``  -<?z*c+<>/T*\n" +
            "oYjx3J3a2anfi77}IneoZenZ2qkhg%W$bhjs,=r*r!=^,=>>>;>><!r*cc!cr!+<;,':_Ikao2jsffcvv''=-.-.,__=;;;>,:^;>;=>;==::':_^''_,^^_-,/<^;>+!>:.--..           `--'-.-_;>;;=^,,^>!!='-.``  .;cz+:``..` .=/Tz!>>><csz\n" +
            "x5ZeuuZ5YjSSSyeeoett[j552kdVRNNNNgDRR8mHKbOGqf37J?r>_'_,^=>zs?zc+^cou/Lc,'^,,:```...-'+iJT|7LsL*5yT*T)yf/sz!?v3n?c;;^_,=---` `                     ` `'':,,==;==^,^=cL*,-``    `,*/,.   `   ,c*!+<;;>rT+\n" +
            "2yoeu1IIfi(7)JJ7(77J|C3tA$UOUG92YDW0#MNWQQ%Mi366ez]B&&@@@@&&&&WR[((*,>^:'-..` `..:cfxyaq962jxkXRD#bt7|ehU9[no)|IL(Z[uCz_-.```                     ````',:,,>;;;==^=>!z+,.       ,c*,`  ``   ,c!>;=^^>/!,\n" +
            "8X92wq2ay]aY[[llunyqw6UG6bbpdkkOGGKKKKAUAbUGOVOOVVOh]VUHNQQWQQ%mi>==<,...```.=2ZT|Cf1t5S9dwYltYPGKHHm8RRmUOpPEjYnnYjSpOp4qnuYnr-.``        `  `    ``.':_,,==;=^^^^;rc;'`     ``=c!'    `   ,*r<=^^;</<_\n" +
            "AXRXKGyEpOdd49ddpXRXOGUAKKAGGKbp9p0%&%Q0ggWWW0u(a21<u0NWWWNWQ%AC====^:..-.` )99Ub4]ICoJ=+<ixaxpV5qX8RRXbkx2k99hh9khhwaf(iCC{IjqhPSI+-         ``.--'--_;>^^^<<r!>=;;+r/v7F)r:``_+?*'`  _Jahwtz+;^,_=+*+'\n" +
            "9UDK]2hm0NNBO9OURHHDKdOUAKKAbbbGVVVO4wh4OAOh6kw222Eh44MQWWNNWg[>!,=,'.-'__.-pg#8HXXXXHAAAKXmmXOVOX8$#mAOAX88mbUpPEx25e[a4UAKAGV96Sx(''````    `..--,:'';;==^,zlPXX8X8HApS]qpUKU[*rvJu]q6pUUO66Sa[s<<**<-\n" +
            "gggg0MBHKR$RGd$0OppppVppbbUAbpk2tjGP5n133Cs*vL{Z|eqS}+A%WWNNW8ic/=+;:-;r>' JXUp9VGbUKXm88mKbqO$m8D#8HKGGUAX8RD#DDR8RRRR8mXAGO9q2an)^--``````` `.'_=;>=;<=;;=>==^^,=rcLZdUKRRmmKbUmUwPpOOGOpdd9PqE2j1(/,-\n" +
            "MM000M$V]XWNDmKGppGUKXXHHA4qjCzc**z!(6WQ%QQQNNNMa/x9]sJ#QWNNQ%2^/+r+>*L)/<_.EdqSqwqhVGUUH8U2})v15Ytv*L3y6XXXHXXXKAUbbG4d9h6kqqyYt{+-_'.````````-_._-TaooSVOX8m8DRmXXX4ns=JPVdpGUV42f[aE9OOd62Zult7L*>^- \n" +
            "000ggNWNDUUHXKAUbGGGV49Se(//TJ1qgQ%%QQQQQWWWWQNNm7fS6u>dWQW0MNWgt^_=;>s?==/,=[Eq6h6P69V4VpOUAAR#$D#RmmHHXmXAbbGGp4d4VdddV96qyYnr_:_,^:..````  ``---.;cLzLJf5SZ/=>z7[EGXXKUOdd9VOVqoJenn[Z5xZeli)s*<'..` \n" +
            "N0dGXmHKXHHHmGVV9hk]5T)]AB&&&%%%%%%%%%%%%%%%%QWWWb?lqys[gN0gg$GhKX$%BOxI<,*!_.TE6qPdVp4k}?++><!>>!<<+czuhpVpOpVVV499hhh6wE]x[>:,,<^_>::-`  ` `.-;Jv(i5VKKHm8RD8RK4j{r>)S9VUKAUAAAUU2JvLC|||F7T*+;'.``  .\n" +
            "KKXXKAUUp49kY|sx888mA$NWWWQ%%%%%%%%%%QQQWWWQQQWNW$I|E]s1$Ng$$Bg$AbRNWN00ggNQW0g0huy299dV9Y[ZYo5]yx5e{IjOXmmAOp49hh9996kEytT;^,_,^,,=_'--..```.-,,^=^__,;>>+=^+F[Y9OGUGOGAXXKXHKGpw2Sj}tu[u}}tCL/=-```  `\n" +
            "KKAbOd6j){6UV4bX8Rm88KBWWQ%&%%QQQQQQ%%QQQQQQWWNMMm1Iou7JX0BBg0g$A9XWWNNNNNNNW$6jAdEGAV66kdpVd9h9hhhd44VVVV4d9hhhd96kSo)r!<=::''---'::,^'--.````     ```` ```.``</z/c?CahObUbp969hhPkwC.--''':_____::::::\n" +
            "phwy|IOApGAH88R$0MMMg8$NWW%%QQQWWWWWWNNN0ggBggMMMNNWWQQQQWNMg$#DDD$Bgg$$#D8m8RBpfan*LEpp4pGUKXHmmHXXKKbbp9h6h6wyIT/z*/s*+T1jtab88ktv_'__-.`` ``  `````   `` ``` ./T/*cfE4GAKAGOV99qwEku-````.-````      \n" +
            "|ZAAUGOmDg00g$0bJ!LyA$HHmHHHHXKKAKKUAAKKKKKXKKKKKKKAAmXKKKKAAUUUAKKHRRmHKKKKAAKm0H(J(3iFv1eoYj]2]2]jxILz/zsTvvLLT|1o3]Ou{lyax2PP94OGbUAKR$g0MNUyT,` ` `--''--''---.-!TLv[oaP9d4VpVOdw2wh[,`.`      ```` \n" +
            "X8$0N$d(csTc_-```      .xUbUUUbbUKKKKAKKKKKKKXXKKXKKKXXUUKKKKHHHXXXXGGGUAAAUAAKXXmKkSaF!Tn]at?rrrrz?szTzLJu]2S6pOXGqG$V]kbAAKKXXXXKAKXm8D$$$$DmXXKAbbOVVOGpVpbXR$Vi_```'/J3ZaS44ppbAAKXHAOG9qAW0}.````  \n" +
            "._rr^-`                  ^6KUUUUUAKKUAAUUAAAKXKKKAKKKAKKKKKKKKKKKKKKKXXXXXXXXXXXXmXHmKh[v/**JyuLa9kZEXbYSha594]y9KmXKXXKKAUbUUUUAXXXXXKXHHmmHXKXKKAKKKAAGphqyot}}CfnE64Kmpl/,!(Z]PVOAXXKXGG9x}qm5aXs` ` \n" +
            "                        ```^wkdVOGUAUAAKAKKKKKKKAKKXXXKXXXXHHXXXXXKXXXXXHHHHHHHHHmmmmmmmmHmmmHHHHmHXXXHHHHHXXXXXXXXXXXXXXHHXKHXXXKXXXKKXHHHXXHXKXKKKKKXKKAAAUGphautuC)TvTs?/c;IC{oywyPhyyS4ZZe2hGk}!:'.-\n" +
            "                            _{A9iI]h4OOGGUUAUXKKKKKKXXXHHHHmHHHHmXXXXHHXXXKXHmXHHXXXXXKXHmmXXXXXKHXXXXXXXXKKXKKAKKKKKKKKKKAAUKXKXXXXXKKAAKKXXXXKAAUAAAKAAUGUUUUUUbd])'`        .,^+*|]dGKKXXXKKAGp4h9bP<\n" +
            "                             <nouZvc{Y69VpUAAAKKKXXXXXXHHHXXXHHXXHmmmmmmHXXmHHHXXmXXXXXXXXXXXXXXKKXXXXXXKKKKAAAAKAUUUbUUUUAAAUAKXHXXXXKKKKXXKKKKKKKAAUAAAGUUbGGA6c  `               .!e2SPVGbKXKKKAbppd4\n" +
            "                        `-1['>/*zFeFss|eEkhdVOGUUAXXKXXXKKKXXHHHmmHHHHHmHHHmHHXXHmmmHHmHmmXHHHXXXKKXXXKAAUUUUUUAAUUKXKXXXXXXXXXXXXXXXXHXKKKKKKAAAAUUUUGbGp44dP7````                    =1j]k9dGGGAAKUbGO\n" +
            "                 *e2Hgg$$$$#DRG3J1ut|||lnjEk69pObGGbGGGUUbUAKKKKKXHHXH88mmRmmm88mmmm88mmmmm88mHXHHHKAHHKKXXXHXXXXXXXXXXXXKKKKKKKKKKAKKUUKKAAAUUUUpGGGVV49k2a3-                           -7Z]k9dVGUKXXKA\n" +
            "  ` `r6UH$%MBB000gDRR8mmmmmUhqPhO2Z2qwxeu3ir--.`                                                                                ``.,^r{24OVd9d49hdV9hPS2xn|v_                              `!naw69VOAKKK\n" +
            "0gg###D8ADg$$$BBB#mXUGGp466VG6wqk6wv,-```                                                                                                      ` `_sT?*/*TTi^-'.`                             /3xSP9pbGA\n" +
            ":!FtYa2E22d4Vhqyu}3IF*<=:.```  ``.:''.``.-_`                                                                                                          .'^,:':_:::'.`                           `z5w694OG\n" +
            ",,____::___''-........``......``-';+_':,^__.                                                                                                           `-___::__:__,___'`                       `<FnawdO\n" +
            ",,,,,_::::_:-....```````````     .;L=^^_-                                                                                              `````           '7)/+;=^,,,^^^^^,,,,,'`                  `!}1eyk9\n" +
            "_,,=,,,,_:'-`       ```````````.-`    `.``                                                                                             ..              .1[tf1n[tl[[e[l133[iJzzc++.               ``.zuYS\n";
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

  private static String getDefaultFooter() {
    return "                                                                  ________ ___       ________  _____ ______   _______   ________\n" +
            "                                                                 |\\  _____\\\\  \\     |\\   __  \\|\\   _ \\  _   \\|\\  ___ \\ |\\   ____\\\n" +
            "                                                                 \\ \\  \\__/\\ \\  \\    \\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|\\ \\  \\___|_\n" +
            "                                                                  \\ \\   __\\\\ \\  \\    \\ \\   __  \\ \\  \\\\|__| \\  \\ \\  \\_|/_\\ \\_____  \\\n" +
            "                                                                   \\ \\  \\_| \\ \\  \\____\\ \\  \\ \\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\|____|\\  \\\n" +
            "                                                                    \\ \\__\\   \\ \\_______\\ \\__\\ \\__\\ \\__\\    \\ \\__\\ \\_______\\____\\_\\  \\\n" +
            "                                                                     \\|__|    \\|_______|\\|__|\\|__|\\|__|     \\|__|\\|_______|\\_________\\\n" +
            "                                                                                                                          \\|_________|\n";
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