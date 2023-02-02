/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.code_gen_test_00
import lupos.endpoint.LuposdateEndpoint
import lupos.shared.EPartitionModeExt
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.Luposdate3000Instance
import lupos.shared.inline.MyPrintWriter
import lupos.shared.myPrintStackTraceAndThrowAgain
import kotlin.test.Test

public class syntaxupdateother01 {
    internal val query = "BASE <http://foo.com/> \n" +
        "INSERT DATA { \n" +
        "  GRAPH <http://example.com/data> { \n" +
        "    <a> <b> 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, \n" +
        "            10, 11, 12, 13, 14, 15, 16, 17, 18, 19, \n" +
        "            20, 21, 22, 23, 24, 25, 26, 27, 28, 29 . \n" +
        "    <c> <d> \"000\"; <d> \"001\"; <d> \"002\"; <d> \"003\"; <d> \"004\"; <d> \"005\"; <d> \"006\"; <d> \"007\"; <d> \"008\"; <d> \"009\"; \n" +
        "        <d> \"010\"; <d> \"011\"; <d> \"012\"; <d> \"013\"; <d> \"014\"; <d> \"015\"; <d> \"016\"; <d> \"017\"; <d> \"018\"; <d> \"019\"; \n" +
        "        <d> \"020\"; <d> \"021\"; <d> \"022\"; <d> \"023\"; <d> \"024\"; <d> \"025\"; <d> \"026\"; <d> \"027\"; <d> \"028\"; <d> \"029\"; \n" +
        "        <d> \"030\"; <d> \"031\"; <d> \"032\"; <d> \"033\"; <d> \"034\"; <d> \"035\"; <d> \"036\"; <d> \"037\"; <d> \"038\"; <d> \"039\"; \n" +
        "        <d> \"040\"; <d> \"041\"; <d> \"042\"; <d> \"043\"; <d> \"044\"; <d> \"045\"; <d> \"046\"; <d> \"047\"; <d> \"048\"; <d> \"049\"; \n" +
        "        <d> \"050\"; <d> \"051\"; <d> \"052\"; <d> \"053\"; <d> \"054\"; <d> \"055\"; <d> \"056\"; <d> \"057\"; <d> \"058\"; <d> \"059\"; \n" +
        "        <d> \"060\"; <d> \"061\"; <d> \"062\"; <d> \"063\"; <d> \"064\"; <d> \"065\"; <d> \"066\"; <d> \"067\"; <d> \"068\"; <d> \"069\"; \n" +
        "        <d> \"070\"; <d> \"071\"; <d> \"072\"; <d> \"073\"; <d> \"074\"; <d> \"075\"; <d> \"076\"; <d> \"077\"; <d> \"078\"; <d> \"079\"; \n" +
        "        <d> \"080\"; <d> \"081\"; <d> \"082\"; <d> \"083\"; <d> \"084\"; <d> \"085\"; <d> \"086\"; <d> \"087\"; <d> \"088\"; <d> \"089\"; \n" +
        "        <d> \"090\"; <d> \"091\"; <d> \"092\"; <d> \"093\"; <d> \"094\"; <d> \"095\"; <d> \"096\"; <d> \"097\"; <d> \"098\"; <d> \"099\"; \n" +
        "        <d> \"100\"; <d> \"101\"; <d> \"102\"; <d> \"103\"; <d> \"104\"; <d> \"105\"; <d> \"106\"; <d> \"107\"; <d> \"108\"; <d> \"109\"; \n" +
        "        <d> \"110\"; <d> \"111\"; <d> \"112\"; <d> \"113\"; <d> \"114\"; <d> \"115\"; <d> \"116\"; <d> \"117\"; <d> \"118\"; <d> \"119\"; \n" +
        "        <d> \"120\"; <d> \"121\"; <d> \"122\"; <d> \"123\"; <d> \"124\"; <d> \"125\"; <d> \"126\"; <d> \"127\"; <d> \"128\"; <d> \"129\"; \n" +
        "        <d> \"130\"; <d> \"131\"; <d> \"132\"; <d> \"133\"; <d> \"134\"; <d> \"135\"; <d> \"136\"; <d> \"137\"; <d> \"138\"; <d> \"139\"; \n" +
        "        <d> \"140\"; <d> \"141\"; <d> \"142\"; <d> \"143\"; <d> \"144\"; <d> \"145\"; <d> \"146\"; <d> \"147\"; <d> \"148\"; <d> \"149\"; \n" +
        "        <d> \"150\"; <d> \"151\"; <d> \"152\"; <d> \"153\"; <d> \"154\"; <d> \"155\"; <d> \"156\"; <d> \"157\"; <d> \"158\"; <d> \"159\"; \n" +
        "        <d> \"160\"; <d> \"161\"; <d> \"162\"; <d> \"163\"; <d> \"164\"; <d> \"165\"; <d> \"166\"; <d> \"167\"; <d> \"168\"; <d> \"169\"; \n" +
        "        <d> \"170\"; <d> \"171\"; <d> \"172\"; <d> \"173\"; <d> \"174\"; <d> \"175\"; <d> \"176\"; <d> \"177\"; <d> \"178\"; <d> \"179\"; \n" +
        "        <d> \"180\"; <d> \"181\"; <d> \"182\"; <d> \"183\"; <d> \"184\"; <d> \"185\"; <d> \"186\"; <d> \"187\"; <d> \"188\"; <d> \"189\"; \n" +
        "        <d> \"190\"; <d> \"191\"; <d> \"192\"; <d> \"193\"; <d> \"194\"; <d> \"195\"; <d> \"196\"; <d> \"197\"; <d> \"198\"; <d> \"199\"; \n" +
        "        <d> \"200\"; <d> \"201\"; <d> \"202\"; <d> \"203\"; <d> \"204\"; <d> \"205\"; <d> \"206\"; <d> \"207\"; <d> \"208\"; <d> \"209\"; \n" +
        "        <d> \"210\"; <d> \"211\"; <d> \"212\"; <d> \"213\"; <d> \"214\"; <d> \"215\"; <d> \"216\"; <d> \"217\"; <d> \"218\"; <d> \"219\"; \n" +
        "        <d> \"220\"; <d> \"221\"; <d> \"222\"; <d> \"223\"; <d> \"224\"; <d> \"225\"; <d> \"226\"; <d> \"227\"; <d> \"228\"; <d> \"229\"; \n" +
        "        <d> \"230\"; <d> \"231\"; <d> \"232\"; <d> \"233\"; <d> \"234\"; <d> \"235\"; <d> \"236\"; <d> \"237\"; <d> \"238\"; <d> \"239\"; \n" +
        "        <d> \"240\"; <d> \"241\"; <d> \"242\"; <d> \"243\"; <d> \"244\"; <d> \"245\"; <d> \"246\"; <d> \"247\"; <d> \"248\"; <d> \"249\"; \n" +
        "        <d> \"250\"; <d> \"251\"; <d> \"252\"; <d> \"253\"; <d> \"254\"; <d> \"255\"; <d> \"256\"; <d> \"257\"; <d> \"258\"; <d> \"259\"; \n" +
        "        <d> \"260\"; <d> \"261\"; <d> \"262\"; <d> \"263\"; <d> \"264\"; <d> \"265\"; <d> \"266\"; <d> \"267\"; <d> \"268\"; <d> \"269\"; \n" +
        "        <d> \"270\"; <d> \"271\"; <d> \"272\"; <d> \"273\"; <d> \"274\"; <d> \"275\"; <d> \"276\"; <d> \"277\"; <d> \"278\"; <d> \"279\"; \n" +
        "        <d> \"280\"; <d> \"281\"; <d> \"282\"; <d> \"283\"; <d> \"284\"; <d> \"285\"; <d> \"286\"; <d> \"287\"; <d> \"288\"; <d> \"289\"; \n" +
        "        <d> \"290\"; <d> \"291\"; <d> \"292\"; <d> \"293\"; <d> \"294\"; <d> \"295\"; <d> \"296\"; <d> \"297\"; <d> \"298\"; <d> \"299\". \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . <e> <f> <g> . \n" +
        "    <e> <f> <g000> . <e> <f> <g001> . <e> <f> <g002> . <e> <f> <g003> . <e> <f> <g004> . \n" +
        "    <e> <f> <g005> . <e> <f> <g006> . <e> <f> <g007> . <e> <f> <g008> . <e> <f> <g009> . \n" +
        "    <e> <f> <g010> . <e> <f> <g011> . <e> <f> <g012> . <e> <f> <g013> . <e> <f> <g014> . \n" +
        "    <e> <f> <g015> . <e> <f> <g016> . <e> <f> <g017> . <e> <f> <g018> . <e> <f> <g019> . \n" +
        "    <e> <f> <g020> . <e> <f> <g021> . <e> <f> <g022> . <e> <f> <g023> . <e> <f> <g024> . \n" +
        "    <e> <f> <g025> . <e> <f> <g026> . <e> <f> <g027> . <e> <f> <g028> . <e> <f> <g029> . \n" +
        "    <e> <f> <g030> . <e> <f> <g031> . <e> <f> <g032> . <e> <f> <g033> . <e> <f> <g034> . \n" +
        "    <e> <f> <g035> . <e> <f> <g036> . <e> <f> <g037> . <e> <f> <g038> . <e> <f> <g039> . \n" +
        "    <e> <f> <g040> . <e> <f> <g041> . <e> <f> <g042> . <e> <f> <g043> . <e> <f> <g044> . \n" +
        "    <e> <f> <g045> . <e> <f> <g046> . <e> <f> <g047> . <e> <f> <g048> . <e> <f> <g049> . \n" +
        "    <e> <f> <g050> . <e> <f> <g051> . <e> <f> <g052> . <e> <f> <g053> . <e> <f> <g054> . \n" +
        "    <e> <f> <g055> . <e> <f> <g056> . <e> <f> <g057> . <e> <f> <g058> . <e> <f> <g059> . \n" +
        "    <e> <f> <g060> . <e> <f> <g061> . <e> <f> <g062> . <e> <f> <g063> . <e> <f> <g064> . \n" +
        "    <e> <f> <g065> . <e> <f> <g066> . <e> <f> <g067> . <e> <f> <g068> . <e> <f> <g069> . \n" +
        "    <e> <f> <g070> . <e> <f> <g071> . <e> <f> <g072> . <e> <f> <g073> . <e> <f> <g074> . \n" +
        "    <e> <f> <g075> . <e> <f> <g076> . <e> <f> <g077> . <e> <f> <g078> . <e> <f> <g079> . \n" +
        "    <e> <f> <g080> . <e> <f> <g081> . <e> <f> <g082> . <e> <f> <g083> . <e> <f> <g084> . \n" +
        "    <e> <f> <g085> . <e> <f> <g086> . <e> <f> <g087> . <e> <f> <g088> . <e> <f> <g089> . \n" +
        "    <e> <f> <g090> . <e> <f> <g091> . <e> <f> <g092> . <e> <f> <g093> . <e> <f> <g094> . \n" +
        "    <e> <f> <g095> . <e> <f> <g096> . <e> <f> <g097> . <e> <f> <g098> . <e> <f> <g099> . \n" +
        "    <e> <f> <g100> . <e> <f> <g101> . <e> <f> <g102> . <e> <f> <g103> . <e> <f> <g104> . \n" +
        "    <e> <f> <g105> . <e> <f> <g106> . <e> <f> <g107> . <e> <f> <g108> . <e> <f> <g109> . \n" +
        "    <e> <f> <g110> . <e> <f> <g111> . <e> <f> <g112> . <e> <f> <g113> . <e> <f> <g114> . \n" +
        "    <e> <f> <g115> . <e> <f> <g116> . <e> <f> <g117> . <e> <f> <g118> . <e> <f> <g119> . \n" +
        "    <e> <f> <g120> . <e> <f> <g121> . <e> <f> <g122> . <e> <f> <g123> . <e> <f> <g124> . \n" +
        "    <e> <f> <g125> . <e> <f> <g126> . <e> <f> <g127> . <e> <f> <g128> . <e> <f> <g129> . \n" +
        "    <e> <f> <g130> . <e> <f> <g131> . <e> <f> <g132> . <e> <f> <g133> . <e> <f> <g134> . \n" +
        "    <e> <f> <g135> . <e> <f> <g136> . <e> <f> <g137> . <e> <f> <g138> . <e> <f> <g139> . \n" +
        "    <e> <f> <g140> . <e> <f> <g141> . <e> <f> <g142> . <e> <f> <g143> . <e> <f> <g144> . \n" +
        "    <e> <f> <g145> . <e> <f> <g146> . <e> <f> <g147> . <e> <f> <g148> . <e> <f> <g149> . \n" +
        "    <e> <f> <g150> . <e> <f> <g151> . <e> <f> <g152> . <e> <f> <g153> . <e> <f> <g154> . \n" +
        "    <e> <f> <g155> . <e> <f> <g156> . <e> <f> <g157> . <e> <f> <g158> . <e> <f> <g159> . \n" +
        "    <e> <f> <g160> . <e> <f> <g161> . <e> <f> <g162> . <e> <f> <g163> . <e> <f> <g164> . \n" +
        "    <e> <f> <g165> . <e> <f> <g166> . <e> <f> <g167> . <e> <f> <g168> . <e> <f> <g169> . \n" +
        "    <e> <f> <g170> . <e> <f> <g171> . <e> <f> <g172> . <e> <f> <g173> . <e> <f> <g174> . \n" +
        "    <e> <f> <g175> . <e> <f> <g176> . <e> <f> <g177> . <e> <f> <g178> . <e> <f> <g179> . \n" +
        "    <e> <f> <g180> . <e> <f> <g181> . <e> <f> <g182> . <e> <f> <g183> . <e> <f> <g184> . \n" +
        "    <e> <f> <g185> . <e> <f> <g186> . <e> <f> <g187> . <e> <f> <g188> . <e> <f> <g189> . \n" +
        "    <e> <f> <g190> . <e> <f> <g191> . <e> <f> <g192> . <e> <f> <g193> . <e> <f> <g194> . \n" +
        "    <e> <f> <g195> . <e> <f> <g196> . <e> <f> <g197> . <e> <f> <g198> . <e> <f> <g199> . \n" +
        "    <e> <f> <g200> . <e> <f> <g201> . <e> <f> <g202> . <e> <f> <g203> . <e> <f> <g204> . \n" +
        "    <e> <f> <g205> . <e> <f> <g206> . <e> <f> <g207> . <e> <f> <g208> . <e> <f> <g209> . \n" +
        "    <e> <f> <g210> . <e> <f> <g211> . <e> <f> <g212> . <e> <f> <g213> . <e> <f> <g214> . \n" +
        "    <e> <f> <g215> . <e> <f> <g216> . <e> <f> <g217> . <e> <f> <g218> . <e> <f> <g219> . \n" +
        "    <e> <f> <g220> . <e> <f> <g221> . <e> <f> <g222> . <e> <f> <g223> . <e> <f> <g224> . \n" +
        "    <e> <f> <g225> . <e> <f> <g226> . <e> <f> <g227> . <e> <f> <g228> . <e> <f> <g229> . \n" +
        "    <e> <f> <g230> . <e> <f> <g231> . <e> <f> <g232> . <e> <f> <g233> . <e> <f> <g234> . \n" +
        "    <e> <f> <g235> . <e> <f> <g236> . <e> <f> <g237> . <e> <f> <g238> . <e> <f> <g239> . \n" +
        "    <e> <f> <g240> . <e> <f> <g241> . <e> <f> <g242> . <e> <f> <g243> . <e> <f> <g244> . \n" +
        "    <e> <f> <g245> . <e> <f> <g246> . <e> <f> <g247> . <e> <f> <g248> . <e> <f> <g249> . \n" +
        "    <e> <f> <g250> . <e> <f> <g251> . <e> <f> <g252> . <e> <f> <g253> . <e> <f> <g254> . \n" +
        "    <e> <f> <g255> . <e> <f> <g256> . <e> <f> <g257> . <e> <f> <g258> . <e> <f> <g259> . \n" +
        "    <e> <f> <g260> . <e> <f> <g261> . <e> <f> <g262> . <e> <f> <g263> . <e> <f> <g264> . \n" +
        "    <e> <f> <g265> . <e> <f> <g266> . <e> <f> <g267> . <e> <f> <g268> . <e> <f> <g269> . \n" +
        "    <e> <f> <g270> . <e> <f> <g271> . <e> <f> <g272> . <e> <f> <g273> . <e> <f> <g274> . \n" +
        "    <e> <f> <g275> . <e> <f> <g276> . <e> <f> <g277> . <e> <f> <g278> . <e> <f> <g279> . \n" +
        "    <e> <f> <g280> . <e> <f> <g281> . <e> <f> <g282> . <e> <f> <g283> . <e> <f> <g284> . \n" +
        "    <e> <f> <g285> . <e> <f> <g286> . <e> <f> <g287> . <e> <f> <g288> . <e> <f> <g289> . \n" +
        "    <e> <f> <g290> . <e> <f> <g291> . <e> <f> <g292> . <e> <f> <g293> . <e> <f> <g294> . \n" +
        "    <e> <f> <g295> . <e> <f> <g296> . <e> <f> <g297> . <e> <f> <g298> . <e> <f> <g299> . \n" +
        "    <e> <f> <g300> . <e> <f> <g301> . <e> <f> <g302> . <e> <f> <g303> . <e> <f> <g304> . \n" +
        "    <e> <f> <g305> . <e> <f> <g306> . <e> <f> <g307> . <e> <f> <g308> . <e> <f> <g309> . \n" +
        "    <e> <f> <g310> . <e> <f> <g311> . <e> <f> <g312> . <e> <f> <g313> . <e> <f> <g314> . \n" +
        "    <e> <f> <g315> . <e> <f> <g316> . <e> <f> <g317> . <e> <f> <g318> . <e> <f> <g319> . \n" +
        "    <e> <f> <g320> . <e> <f> <g321> . <e> <f> <g322> . <e> <f> <g323> . <e> <f> <g324> . \n" +
        "    <e> <f> <g325> . <e> <f> <g326> . <e> <f> <g327> . <e> <f> <g328> . <e> <f> <g329> . \n" +
        "    <e> <f> <g330> . <e> <f> <g331> . <e> <f> <g332> . <e> <f> <g333> . <e> <f> <g334> . \n" +
        "    <e> <f> <g335> . <e> <f> <g336> . <e> <f> <g337> . <e> <f> <g338> . <e> <f> <g339> . \n" +
        "    <e> <f> <g340> . <e> <f> <g341> . <e> <f> <g342> . <e> <f> <g343> . <e> <f> <g344> . \n" +
        "    <e> <f> <g345> . <e> <f> <g346> . <e> <f> <g347> . <e> <f> <g348> . <e> <f> <g349> . \n" +
        "    <e> <f> <g350> . <e> <f> <g351> . <e> <f> <g352> . <e> <f> <g353> . <e> <f> <g354> . \n" +
        "    <e> <f> <g355> . <e> <f> <g356> . <e> <f> <g357> . <e> <f> <g358> . <e> <f> <g359> . \n" +
        "    <e> <f> <g360> . <e> <f> <g361> . <e> <f> <g362> . <e> <f> <g363> . <e> <f> <g364> . \n" +
        "    <e> <f> <g365> . <e> <f> <g366> . <e> <f> <g367> . <e> <f> <g368> . <e> <f> <g369> . \n" +
        "    <e> <f> <g370> . <e> <f> <g371> . <e> <f> <g372> . <e> <f> <g373> . <e> <f> <g374> . \n" +
        "    <e> <f> <g375> . <e> <f> <g376> . <e> <f> <g377> . <e> <f> <g378> . <e> <f> <g379> . \n" +
        "    <e> <f> <g380> . <e> <f> <g381> . <e> <f> <g382> . <e> <f> <g383> . <e> <f> <g384> . \n" +
        "    <e> <f> <g385> . <e> <f> <g386> . <e> <f> <g387> . <e> <f> <g388> . <e> <f> <g389> . \n" +
        "    <e> <f> <g390> . <e> <f> <g391> . <e> <f> <g392> . <e> <f> <g393> . <e> <f> <g394> . \n" +
        "    <e> <f> <g395> . <e> <f> <g396> . <e> <f> <g397> . <e> <f> <g398> . <e> <f> <g399> . \n" +
        "  } \n" +
        "} \n" +
        ""

    @Test
    public fun `syntaxupdateother01 - None - Simple - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_gen_test_00/src/jvmTest/kotlin/lupos/code_gen_test_00/syntaxupdateother01.kt:180"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `syntaxupdateother01 - None - Simple - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_gen_test_00/src/jvmTest/kotlin/lupos/code_gen_test_00/syntaxupdateother01.kt:197"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `syntaxupdateother01 - Thread - BenchmarkFig5 - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.BenchmarkFig5
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_gen_test_00/src/jvmTest/kotlin/lupos/code_gen_test_00/syntaxupdateother01.kt:214"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `syntaxupdateother01 - Thread - BenchmarkFig5 - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.BenchmarkFig5
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_gen_test_00/src/jvmTest/kotlin/lupos/code_gen_test_00/syntaxupdateother01.kt:231"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `syntaxupdateother01 - Thread - PartitionByIDTwiceAllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_gen_test_00/src/jvmTest/kotlin/lupos/code_gen_test_00/syntaxupdateother01.kt:248"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `syntaxupdateother01 - Thread - PartitionByIDTwiceAllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_gen_test_00/src/jvmTest/kotlin/lupos/code_gen_test_00/syntaxupdateother01.kt:265"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `syntaxupdateother01 - Thread - PartitionByID_1_AllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_1_AllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_gen_test_00/src/jvmTest/kotlin/lupos/code_gen_test_00/syntaxupdateother01.kt:282"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `syntaxupdateother01 - Thread - PartitionByID_1_AllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_1_AllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_gen_test_00/src/jvmTest/kotlin/lupos/code_gen_test_00/syntaxupdateother01.kt:299"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `syntaxupdateother01 - Thread - PartitionByID_2_AllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_2_AllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_gen_test_00/src/jvmTest/kotlin/lupos/code_gen_test_00/syntaxupdateother01.kt:316"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `syntaxupdateother01 - Thread - PartitionByID_2_AllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_2_AllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_gen_test_00/src/jvmTest/kotlin/lupos/code_gen_test_00/syntaxupdateother01.kt:333"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `syntaxupdateother01 - Thread - PartitionByID_O_AllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_O_AllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_gen_test_00/src/jvmTest/kotlin/lupos/code_gen_test_00/syntaxupdateother01.kt:350"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `syntaxupdateother01 - Thread - PartitionByID_O_AllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_O_AllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_gen_test_00/src/jvmTest/kotlin/lupos/code_gen_test_00/syntaxupdateother01.kt:367"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `syntaxupdateother01 - Thread - PartitionByID_S_AllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_S_AllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_gen_test_00/src/jvmTest/kotlin/lupos/code_gen_test_00/syntaxupdateother01.kt:384"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `syntaxupdateother01 - Thread - PartitionByID_S_AllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_S_AllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_gen_test_00/src/jvmTest/kotlin/lupos/code_gen_test_00/syntaxupdateother01.kt:401"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `syntaxupdateother01 - Thread - PartitionByKeyAllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_gen_test_00/src/jvmTest/kotlin/lupos/code_gen_test_00/syntaxupdateother01.kt:418"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `syntaxupdateother01 - Thread - PartitionByKeyAllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_gen_test_00/src/jvmTest/kotlin/lupos/code_gen_test_00/syntaxupdateother01.kt:435"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `syntaxupdateother01 - Thread - Simple - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_gen_test_00/src/jvmTest/kotlin/lupos/code_gen_test_00/syntaxupdateother01.kt:452"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `syntaxupdateother01 - Thread - Simple - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_code_gen_test_00/src/jvmTest/kotlin/lupos/code_gen_test_00/syntaxupdateother01.kt:469"/*SOURCE_FILE_END*/) // otherwise this would be silently ignored
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    internal fun normalHelper(instance: Luposdate3000Instance) {
        val buf = MyPrintWriter(false)
        val operator0 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
    }
}
