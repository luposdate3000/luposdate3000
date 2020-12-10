#!/usr/bin/env kotlin
import kotlin.math.log2
import kotlin.math.pow

val dataIn = arrayOf(
//doubleArrayOf(-1.0,32.0,128.0,512.0,2048.0,8196.0,32768.0,131072.0,524288.0,2097152.0),
        doubleArrayOf(-1.0, 32.0, 128.0, 512.0, 2048.0, 8196.0, 32768.0, 131072.0, 524288.0),
/*
doubleArrayOf(0.0,1892.35275650510452694303,3374.56489203923269143484,4223.97188524313182171459,4696.66583692236881038151,4978.15585212089351923758,4847.50712098796073131431,4853.82935586286767150641,4838.38347672689163859597,4733.77262743315913050064),
doubleArrayOf(1.0,995.96484841663998310843,2061.83313873064892280558,2996.69195175445825353392,3440.91230972615155291813,3526.03589645584027903637,3514.42954482514307242676,3530.55732671848112599358,3572.69177316265393835677,2723.19288919872772428216),
doubleArrayOf(2.0,911.19153692590035519157,1954.97382485545901025931,2406.33694396170651640857,2838.22970530944792742533,2894.49339997614937438419,2880.76596109986492088408,2833.94820562901464187679,2667.48113757400592981056,2084.57191648037542306387),
doubleArrayOf(3.0,969.04736732602910650053,1844.63761079585229543937,2257.89138684430551889618,2427.67766169971909341776,2455.95852377245053085543,2390.91223821901903739960,2442.09905251440960545936,2390.38872501446185178633,2469.36688643208833616775),
doubleArrayOf(4.0,911.25364535630154221478,1351.57911069608621885336,1779.04572343204249926747,2118.44434158220252539749,2122.92905616909384683873,2121.92991223061304040515,2052.91554038587832246417,2079.18190013938835458534,2077.30050644586347150151),
doubleArrayOf(5.0,860.58579042050975594475,1230.53660255735038889878,1737.23669572021316589151,1914.91459959614451094517,1879.43923547419003686708,1890.68226403907208339572,1812.73208635493112977620,1816.05769106788369167481,1726.80882793023485118102),
doubleArrayOf(6.0,838.22184312242330605424,1204.70838587869743074251,1552.03781012431512451533,1635.52999185833170052922,1658.00854603924970470867,1609.47012219901902796051,1616.38366482668326153895,1693.24410839945592680308,1644.18166037027299827870),
doubleArrayOf(7.0,849.90586017739745057138,1164.96688290393624835229,1467.59865051368887965280,1496.25047113186709764665,1503.48085888448936978893,1497.77011984557390956344,1464.64865422223055444567,1434.63213449733646197909,1439.68186486023064567284),
doubleArrayOf(8.0,807.14354321486887549568,950.39562118523077601513,1229.59638498662813931327,1381.15308880386203551303,1354.78977117465285882995,1338.54097160138089240794,1363.91522230317816797270,1376.74617880656206791681,1351.64743520168404457246),
doubleArrayOf(9.0,783.10111644376868038769,911.51578968669105822152,1183.45464137892347758619,1267.74124640516122816236,1258.33362904173615814133,1267.69062263892621533499,1237.74707752443158012648,1231.35831258597189818686,1226.97700350620948521934),
*/
        doubleArrayOf(10.0, 753.85326442209872598044, 885.38520321228376347855, 1087.67651630263946460211, 1176.09451471870524225312, 1159.39058257173932123014, 1109.97227955229046109802, 1114.50577185821658792497, 995.96712989761856291504, 1127.69359482442769268139),
        doubleArrayOf(11.0, 750.83333114340278416507, 861.87452365272420868498, 1059.02261320395748278373, 1099.56700151047518997493, 1071.81905807898084584315, 1047.80176428861070916271, 1019.15969264181989307995, 1036.18793091817959602970, 908.29260246539677672387),
        doubleArrayOf(12.0, 674.14554748438544082916, 877.98941127210217619333, 993.99181646537504056729, 1020.56447625294445608454, 993.04639125451848521100, 1031.64083713114713509212, 957.88762024123824983203, 1004.56312755058578085093, 976.45598619759934389969),
        doubleArrayOf(13.0, 640.79281963450715470814, 878.84382119184229042167, 932.98638679563026495880, 943.45345665779537381371, 951.15767302699461568664, 933.49118046867604999554, 887.16439901531849024491, 897.80960492875925675370, 937.90461791407199262056),
        doubleArrayOf(14.0, 623.27190074619358501135, 759.75227517315704041540, 894.52599291267055815291, 914.32851530969952604867, 876.00599433381802744999, 909.42690099323968418877, 864.45269382045127369756, 851.92278546718551036095, 877.57588267021066911124),
        doubleArrayOf(15.0, 597.48909985697903416723, 801.04764214830724214356, 861.76133157982137583471, 861.06411853684196296098, 845.98954847592021878643, 834.95495501513188864973, 834.86837799099945099055, 845.70200708765938100025, 793.73533276321970165393),
        doubleArrayOf(16.0, 551.74937380584193340484, 679.01168762447557381070, 801.68533497860542346542, 802.17434168359388223342, 808.31464779305851713061, 808.71863384778621361170, 798.79739454656225958694, 735.18075926086984970038, 775.60565106277364336876),
        doubleArrayOf(17.0, 539.81744777441633453048, 706.52064863117276492899, 741.87845256321601935175, 793.34390807873208411285, 762.66853449275220838300, 766.69989288435796512634, 721.15221662720281353210, 622.06513556945428776123, 0.0),
        doubleArrayOf(18.0, 529.56766942776188604042, 662.70167834501654302199, 746.35420896007184107073, 732.91677275636952315115, 700.14081932299043306580, 712.21515222067616139678, 701.72533207397027070458, 618.21229942024668772668, 697.89037510072302838641),
        doubleArrayOf(19.0, 502.13409501049937285962, 621.21038370550254491257, 670.42583370636944097145, 685.13164975933037973079, 692.05938672326974253383, 673.73100575440389324893, 691.15631626677168556506, 653.63501465253612346590, 645.33557256172537951217),
        doubleArrayOf(20.0, 495.68184284198986320717, 620.48334659926728362566, 660.11859822759476613087, 680.55354319653685357366, 673.64994656271798891239, 642.38118886899862601087, 666.86383607419927158463, 614.77472102906402823389, 0.0),
        doubleArrayOf(21.0, 495.18278756514810446752, 591.75992063315944468065, 645.20753713379068654533, 659.72499627420308354143, 642.34867341188443967507, 599.96589793836118355032, 618.48744318960901512245, 600.73388053782022269685, 0.0),
        doubleArrayOf(22.0, 454.84275631071582243302, 556.05563492322984693122, 623.32124007018098506198, 621.23149328085335339828, 626.72820301982717343073, 594.62594906017882186166, 584.43358800611364287741, 554.72851697325032523732, 598.05897567209718171286),
        doubleArrayOf(23.0, 457.56329244242709872843, 527.79272017612231955189, 598.17105610569347585596, 599.81183902609751321010, 587.02564145612649604218, 605.48480835588411044987, 592.07595150305881238445, 571.43242451577673495169, 584.57981338341701398501),
        doubleArrayOf(24.0, 422.18989561101517080283, 499.45142752457837933705, 580.73429089061933511847, 588.12953458898596881725, 578.99340144380110557632, 593.68370408286969604462, 559.53130524497371629670, 509.17990262850886054499, 0.0),
        doubleArrayOf(25.0, 416.66092021814199145836, 490.43766706452605011164, 543.99869614392508224036, 548.51229205076239917521, 532.37766573475730872016, 539.05449840978922969112, 540.89153529087388870453, 481.78751648074647001513, 0.0),
        doubleArrayOf(26.0, 404.16091747761554758550, 497.21040104493737883604, 541.76290407453920762516, 536.28076099741571664082, 532.30222843004909955755, 531.80375229027933470712, 509.02991064845269414515, 504.47120395130097322080, 0.0),
        doubleArrayOf(27.0, 384.76218618796094509905, 494.44312675351164633590, 536.77323386710600482312, 509.11278798155769472304, 495.78097824230472315168, 516.94774407881592085323, 490.59201504586839103872, 473.61201959105591278794, 0.0),
        doubleArrayOf(28.0, 375.73143168141130737442, 488.72778683336063692569, 493.52539104237596547138, 511.11979446850824832346, 494.16475433142819642811, 484.62989033358674630298, 480.13909437508408435890, 462.24831850240419972936, 0.0),
        doubleArrayOf(29.0, 385.06479966714998716771, 424.54453270489421305470, 488.96570648796860761929, 493.78953495049735222644, 472.91759368367478535334, 468.96902318911128963198, 472.98024664217133501323, 439.16524874846687411661, 0.0),
        doubleArrayOf(30.0, 357.92280929317940504996, 430.36115305458726687013, 473.38772779239940972337, 462.64560093199031016547, 463.72106102346576959518, 459.84320358412829110355, 463.75181338552829076199, 412.11122354421092116172, 0.0),
        doubleArrayOf(31.0, 333.08555099195042141484, 427.37559502435656253603, 464.49139771221264936417, 478.26107334602255613135, 461.55164061299965734406, 451.59349053272632544834, 442.73417217586623265140, 453.87771789920338106220, 0.0),
        doubleArrayOf(32.0, 337.90232467000205545984, 423.83987397375651314734, 442.17731292561224534013, 432.16702114446297672668, 437.73062932532578102087, 428.64910591297865906128, 437.13465924435329118247, 424.03546666566028915911, 0.0),
        doubleArrayOf(33.0, 314.65303304442101607883, 411.73353280999530911986, 438.91645747542539673329, 425.86067933806600823495, 416.70232944102799464587, 411.84139559540569507460, 424.71869606603458331443, 398.93525775446364670548, 0.0),
        doubleArrayOf(34.0, 314.53242003602968871512, 402.42987156450649018775, 413.70164970565748175917, 409.42895224428889601659, 425.48395289399080078164, 420.68276728994604785577, 398.45023577695026747366, 389.03849796593166531002, 0.0),
        doubleArrayOf(35.0, 319.41945386492268755567, 386.55449061511411049907, 424.80533507922661979761, 413.53759995513944115686, 417.71471288128581277090, 400.12423057110771752413, 405.78608759152403759104, 398.83212382174022739652, 0.0),
        doubleArrayOf(36.0, 301.84582343178417202603, 393.50621871650155342482, 412.09514073481755991791, 394.70440882851139403084, 407.79942499465476903688, 385.48313680889041300354, 387.99152269562231880883, 387.71235635790301429685, 0.0),
        doubleArrayOf(37.0, 293.83946750646770051928, 365.90560374552684973241, 403.67080468262977481506, 379.72829605015360199439, 397.09345066227047154728, 392.11516014453678495055, 313.57711780107683636561, 283.58410723199876856437, 0.0),
        doubleArrayOf(38.0, 296.01116080480698444254, 363.71978111489060418329, 389.24656539534975691746, 372.25231261749213616989, 363.57267892384086758330, 364.68579674638272716779, 372.38778810851841906339, 270.50956156621790032101, 0.0),
        doubleArrayOf(39.0, 283.75668885454802383329, 348.17009111680918545119, 387.35172660095373742123, 375.50447618233310769269, 373.08386458921134734574, 370.57281292557971484422, 377.26998160469296693677, 336.89592917531211807582, 0.0),
        doubleArrayOf(40.0, 281.79996345054474046434, 340.08365377715610485867, 359.20665331184223873678, 374.80419291951402288659, 355.87355784020053047936, 347.79161022298659089446, 338.56845942277327925627, 344.99592042324099517523, 0.0),
)
var dataTmp = Array(dataIn.size) { DoubleArray(dataIn[0].size) }
val dataOut1 = Array(dataIn.size) { DoubleArray(dataIn[0].size) }
val dataOut2 = Array(dataIn.size) { DoubleArray(dataIn[0].size) }

fun initializeBorders(target: Array<DoubleArray>) {
    for (i in 0 until dataIn.size) {
        target[i][0] = dataIn[i][0]
    }
    for (i in 0 until dataIn[0].size) {
        target[0][i] = dataIn[0][i]
    }
}

abstract class Partial_Function {
    abstract fun setK(k: Double)
    abstract fun getK(): Double
    abstract fun f(x: Double, z: Double): Double
    abstract fun print(): String
    abstract fun classname(): String
}

class Partial_FunctionCombined : Partial_Function() {
    var _k = 0.0
    override fun getK() = _k
    override fun setK(k: Double) {
        _k = k
    }

    var parts: List<Partial_Function>? = null
    override fun f(x: Double, z: Double): Double {
        var res = 0.0
        for (p in parts!!) {
            res += p.f(x, z)
        }
        return res
    }

    override fun print(): String {
        var res = ""
        for (p in parts!!) {
            if (res != "") {
                res += " + " + p.print()
            } else {
                res = p.print()
            }
        }
        return res
    }

    override fun classname() = "Partial_FunctionCombined"
}

class Partial_FunctionK : Partial_Function() {
    var _k = 0.0
    override fun getK() = _k
    override fun setK(k: Double) {
        _k = k
    }

    override fun f(x: Double, z: Double) = _k
    override fun print() = "$_k"
    override fun classname() = "Partial_FunctionK"
}


class Partial_FunctionKX : Partial_Function() {
    var _k = 0.0
    override fun getK() = _k
    override fun setK(k: Double) {
        _k = k
    }

    override fun f(x: Double, z: Double) = x * _k
    override fun print() = "$_k * x"
    override fun classname() = "Partial_FunctionKX"
}

class Partial_FunctionK_X : Partial_Function() {
    var _k = 0.0
    override fun getK() = _k
    override fun setK(k: Double) {
        _k = k
    }

    override fun f(x: Double, z: Double) = x.pow(2.0) * _k
    override fun print() = "$_k * x ^ 2"
    override fun classname() = "Partial_FunctionK_X"
}

class Partial_FunctionX_K : Partial_Function() {
    var _k = 0.0
    override fun getK() = _k
    override fun setK(k: Double) {
        _k = k
    }

    override fun f(x: Double, z: Double) = _k * 2.0.pow(x)
    override fun print() = "$_k * 2 ^ x"
    override fun classname() = "Partial_FunctionX_K"
}

class Partial_FunctionKZ : Partial_Function() {
    var _k = 0.0
    override fun getK() = _k
    override fun setK(k: Double) {
        _k = k
    }

    override fun f(x: Double, z: Double) = z * _k
    override fun print() = "$_k * z"
    override fun classname() = "Partial_FunctionKZ"
}

class Partial_FunctionK_Z : Partial_Function() {
    var _k = 0.0
    override fun getK() = _k
    override fun setK(k: Double) {
        _k = k
    }

    override fun f(x: Double, z: Double) = z.pow(2.0) * _k
    override fun print() = "$_k * z ^ 2"
    override fun classname() = "Partial_FunctionK_Z"
}

class Partial_FunctionZ_K : Partial_Function() {
    var _k = 0.0
    override fun getK() = _k
    override fun setK(k: Double) {
        _k = k
    }

    override fun f(x: Double, z: Double) = _k * 2.0.pow(z)
    override fun print() = "$_k * 2 ^ z"
    override fun classname() = "Partial_FunctionZ_K"
}

class Partial_FunctionKXX : Partial_Function() {
    var _k = 0.0
    override fun getK() = _k
    override fun setK(k: Double) {
        _k = k
    }

    override fun f(x: Double, z: Double) = x * x * _k
    override fun print() = "$_k * x * x"
    override fun classname() = "Partial_FunctionKXX"
}

class Partial_FunctionKZZ : Partial_Function() {
    var _k = 0.0
    override fun getK() = _k
    override fun setK(k: Double) {
        _k = k
    }

    override fun f(x: Double, z: Double) = z * z * _k
    override fun print() = "$_k * z * z"
    override fun classname() = "Partial_FunctionKZZ"
}

fun calcError(func: Partial_Function, dIn: Array<DoubleArray>, dOut1: Array<DoubleArray>, dOut2: Array<DoubleArray>): Double {
    var sumSquaredErrors = 0.0
    for (zi in 1 until dIn.size) {
        val z = dIn[zi][0]
        for (xi in 1 until dIn[0].size) {
            val x = dIn[0][xi]
            val target = dIn[zi][xi]
            val actual = func.f(mapX(x), mapZ(z))
            var error = target - actual
            dOut1[zi][xi] = actual
            dOut2[zi][xi] = error
            sumSquaredErrors += error * error
        }
    }
    return sumSquaredErrors
}

inline fun mapX(x: Double) = log2(x)
inline fun mapZ(z: Double) = 1.0 / (1.0 + z)

val ttt = Partial_FunctionK()
calcError(ttt, dataIn, dataOut1, dataTmp)
initializeBorders(dataTmp)

var iterations = 0
var allFunctionParts = mutableListOf<Partial_Function>()
while (true) {
    iterations++
    val funcCombined = Partial_FunctionCombined()
    funcCombined.parts = allFunctionParts
    println("#$iterations f(x,z) = " + funcCombined.print())
    var bestfunction: Partial_Function? = null
    for (function in arrayOf(Partial_FunctionK(), Partial_FunctionKX(), Partial_FunctionKZ(), Partial_FunctionKXX(), Partial_FunctionKZZ(), Partial_FunctionK_X(), Partial_FunctionX_K(), Partial_FunctionK_Z(), Partial_FunctionZ_K())) {
        var stepsize = 0.5
        var lastError = calcError(function, dataTmp, dataOut1, dataOut2)
        loop2@ while (stepsize >= 0.000001) {
            loop@ while (true) {
                var backup = function.getK()
                function.setK(backup + stepsize)
                var error = calcError(function, dataTmp, dataOut1, dataOut2)
                if (error >= lastError) {
                    function.setK(backup - stepsize)
                    error = calcError(function, dataTmp, dataOut1, dataOut2)
                    if (error >= lastError) {
                        function.setK(backup)
                        break@loop
                    }
                }
                if (!error.isFinite()) {
                    break@loop2
                }
                lastError = error
            }
            stepsize = stepsize / 2.0
        }
        println("g(x,z) = " + function.print() + " ::: " + lastError)
        if (bestfunction == null) {
            bestfunction = function
        } else {
            val e1 = calcError(bestfunction!!, dataTmp, dataOut1, dataOut2)
            if (e1 > lastError && lastError.isFinite()) {
                bestfunction = function
            }
        }
    }
    if (bestfunction!!.getK() == 0.0) {
        break
    }
    val dataTmp2 = Array(dataIn.size) { DoubleArray(dataIn[0].size) }
    calcError(bestfunction!!, dataTmp, dataOut1, dataTmp2)
    dataTmp = dataTmp2
    initializeBorders(dataTmp)
    for (f2 in allFunctionParts) {
        if (f2.classname() == bestfunction!!.classname()) {
            f2.setK(f2.getK() + bestfunction!!.getK())
            bestfunction = null
            break
        }
    }
    if (bestfunction != null) {
        allFunctionParts.add(bestfunction!!)
        bestfunction = null
    }
    if (iterations > 2000) {
        break
    }
}
val funcCombined = Partial_FunctionCombined()
funcCombined.parts = allFunctionParts
val finalError = calcError(funcCombined, dataIn, dataOut1, dataOut2)
println("xxxxxxxxxxxxx")
var row = "-1,"
for (xi in 1 until dataIn[0].size) {
    val x = dataIn[0][xi]
    row += "$x,"
}
println(row)
for (zi in 1 until dataIn.size) {
    val z = dataIn[zi][0]
    row = "$z,"
    for (xi in 1 until dataIn[0].size) {
        val x = dataIn[0][xi]
        val target = dataIn[zi][xi]
        row += "$target,"
    }
    println(row)
}
println("xxxxxxxxxxxxx")
row = "-1,"
for (xi in 1 until dataIn[0].size) {
    val x = mapX(dataIn[0][xi])
    row += "$x,"
}
println(row)
for (zi in 1 until dataIn.size) {
    val z = mapZ(dataIn[zi][0])
    row = "$z,"
    for (xi in 1 until dataIn[0].size) {
        val x = dataIn[0][xi]
        val target = dataOut1[zi][xi]
        row += "$target,"
    }
    println(row)
}
println("xxxxxxxxxxxxx")
row = "-1,"
for (xi in 1 until dataIn[0].size) {
    val x = mapX(dataIn[0][xi])
    row += "$x,"
}
println(row)
for (zi in 1 until dataIn.size) {
    val z = mapZ(dataIn[zi][0])
    row = "$z,"
    for (xi in 1 until dataIn[0].size) {
        val x = dataIn[0][xi]
        val target = dataOut2[zi][xi]
        row += "$target,"
    }
    println(row)
}
println("#$iterations f(x,z) = " + funcCombined.print() + " :: " + finalError)
