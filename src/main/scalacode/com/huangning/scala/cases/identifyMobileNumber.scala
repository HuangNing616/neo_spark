package com.huangning.scala.cases

object identifyMobileNumber {

    def isInGoodNumber(s: String): String ={

        // 判断输入的电话号码是不是11位的数字
        val pat = "^\\d{11}$".r
        if(pat.findFirstIn(s) == None){
            return "该号码不满足11位的数字号码"
        }

        // 联通用户的号段
        val liantong_user = Set(186, 185, 156, 131, 130, 155, 132, 176, 166)
        // 移动用户的号段
        val yidong_user = Set(134, 135, 136, 137, 138, 139, 147, 150, 151, 152, 157, 158, 159, 178, 182, 183, 184, 187, 188)
        // 电信用户的号段
        val dianxin_user = Set(189, 181, 180, 153, 133, 177, 173, 199)
        //判断是联通用户，移动用户还是电信用户
        val pre_number = s.slice(0,3).toInt

        // 移动号码的模式串(按优先级别降序）
        val y_pattern1 = "(\\d)\\1(?!\\1)(\\d)\\2$".r   // 匹配尾号AABB模式
        val y_pattern2 = "(\\d)\\1\\1(?!\\1)(\\d)$".r   // 匹配尾号AAAB模式
        val y_pattern3 = "(\\d)(?!\\1)(\\d)\\2\\1$".r   // 匹配尾号ABBA模式
        val y_pattern4 = "(\\d)(?!\\1)(\\d)\\1\\2$".r   // 匹配尾号ABAB模式
        val y_pattern5 = "(\\d)\\1{2}$".r               // 匹配尾号AAAA模式
        val y_pattern6 = "(0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){3}\\d$".r  //匹配尾号ABCD模式
        val y_pattern7 = "(9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){3}\\d$".r  //匹配尾号DCBA模式
        val y_pattern8 = "(\\d)(\\d)\\1(?!\\1)\\d$".r    // 匹配尾号ABAC模式

        val y_pattern9  = "1314".r  // 匹配一生一世
        val y_pattern10  = "520".r  // 匹配我爱你
        val y_pattern11 = "3344".r  // 匹配生生世世
        val y_pattern12 = "888".r   // 匹配发发发
        val y_pattern13 = "666".r   // 匹配六六大顺
        val y_pattern14 = "168".r   // 匹配一路发
        val y_pattern15 = "6868".r  // 匹配要发要发
        val y_pattern16 = "518".r   // 匹配我要发
        val y_pattern17 = "1573".r  // 匹配一往情深

        // 联通号码的模式串(按优先级别降序）
        val l_pattern1 = "(\\d)\\1{4}".r                                                            // 匹配AAAAA模式
        val l_pattern2 = "(\\d)\\1{3}".r                                                            // 匹配AAAA模式
        val l_pattern3 = "(0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){4}".r    // 匹配ABCDE模式
        val l_pattern4 = "(9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){4}".r    // 匹配EDCBA模式
        val l_pattern5 = "(0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){3}".r    // 匹配ABCD模式
        val l_pattern6 = "(9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){3}".r    // 匹配DCBA模式
        val l_pattern7 = "(\\d)\\1(?!\\1)(\\d)\\2".r                                                // 匹配AABB模式
        val l_pattern8 = "(\\d)(?!\\1)(\\d)\\1\\2".r                                                // 匹配ABAB模式
        val l_pattern9 = "(\\d)\\1{2}".r                                                            // 匹配AAA模式
        val l_pattern10 = "(0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){2}$".r  // 匹配ABC模式
        val l_pattern11 = "(9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){2}".r   // 匹配CBA模式
        val l_pattern12 = "(\\d)\\1".r                                                              // 匹配AA模式

        // 电信号码的模式串（按优先级降序）
        val d_pattern1  = "(\\d)(?!\\1)(\\d)(?!\\2)(?!\\1)(\\d)(?=\\1)(\\d)(?=\\2)(\\d)(?=\\3)(\\d)".r       // 匹配ABCABC
        val d_pattern2  = "(\\d)\\1\\1(?!\\1)(\\d)\\2\\2".r                                                  // 匹配AAABBB
        val d_pattern3  = "(\\d)\\1(?!\\1)(\\d)\\2(?!\\2)(?!\\1)(\\d)\\3".r                                  // 匹配AABBCC
        val d_pattern4  = "(\\d)\\1(?!\\1)(\\d)\\2".r                                                        // 匹配AABB模式
        val d_pattern5  = "(\\d)(?!\\1)(\\d)\\1\\2".r                                                        // 匹配ABAB模式
        val d_pattern6  = "(\\d)(?!\\1)(\\d)\\2\\1".r                                                        // 匹配ABBA模式
        val d_pattern7  = "(\\d)\\1{3}".r                                                                    // 匹配AAAA模式
        val d_pattern8  = "(9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){2}".r            // 匹配CBA模式
        val d_pattern9  = "(\\d)\\1{2}".r                                                                    // 匹配AAA模式
        val d_pattern10 = "(0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){2}".r            // 匹配ABC模式
        val d_pattern11 = "(\\d)\\1".r                                                                       // 匹配AA模式

        val d_pattern12 = "5201314".r   // 匹配5201314
        val d_pattern13 = "5211314".r   // 匹配5311314
        val d_pattern14 = "1314".r      // 匹配1314
        val d_pattern15 = "530".r       // 匹配530
        val d_pattern16 = "213".r       // 匹配213
        val d_pattern17 = "927".r       // 匹配927
        val d_pattern18 = "512".r       // 匹配512
        val d_pattern19 = "758".r       // 匹配758
        val d_pattern20 = "521".r       // 匹配521
        val d_pattern21 = "520".r       // 匹配520
        val d_pattern22 = "99".r        // 匹配99


        // 创建一个移动号码的模式串列表
        val yidong_li = List((y_pattern9, "一生一世"), (y_pattern10, "我爱你"), (y_pattern11, "生生世世"),(y_pattern12, "发发发"), (y_pattern13, "六六大顺"), (y_pattern14, "一路发"),
                            (y_pattern15, "要发要发"), (y_pattern16, "我要发"), (y_pattern17, "一往情深"), (y_pattern1, "AABB"), (y_pattern2, "AAAB"), (y_pattern3, "ABBA"),
                            (y_pattern4,"ABAB"), (y_pattern5, "AAAA"), (y_pattern6, "ABCD"), (y_pattern7, "DCBA"), (y_pattern8, "ABAC"))

        // 创建一个联通号码的模式串列表
        val liantong_li = List((l_pattern1, "AAAAA"), (l_pattern2, "AAAA"), (l_pattern3, "ABCDE"), (l_pattern4, "EDCBA"), (l_pattern5, "ABCD"), (l_pattern6, "DCBA"),
                            (l_pattern7, "AAA"), (l_pattern8, "AABB"),(l_pattern9, "ABAB"), (l_pattern10, "ABC"), (l_pattern11, "CBA"), (l_pattern12, "AA"))


        val dianxin_li = List((d_pattern12, "5201314"),(d_pattern13, "5311314"),(d_pattern14, "1314"), (d_pattern15, "530"), (d_pattern16, "213"), (d_pattern17, "927"),
                            (d_pattern18, "512"), (d_pattern19, "758"),(d_pattern20, "521"), (d_pattern21, "520"), (d_pattern22, "99"), (d_pattern1, "ABCABC"),
                            (d_pattern2, "AAABBB"), (d_pattern3, "AABBCC"), (d_pattern4, "AABB"), (d_pattern5, "ABAB"), (d_pattern6, "ABBA"),
                            (d_pattern7, "AAAA"),(d_pattern8, "CBA"), (d_pattern9, "AAA"), (d_pattern10, "ABC"), (d_pattern11, "AA"))

        // 创建一个长度为10的map，键是0-9，值是每个数字出现的次数
        var count_map = scala.collection.mutable.Map[Int, Int]()
        // 初始化一个字符串用来拼接每个数字出现则次数
        var str = ""

        for(i <- 0 to 9){
            count_map += (i -> 0)
        }

        // 判断是否是移动用户
        if(yidong_user.contains(pre_number)){
            for(yidong_pat <- yidong_li){
                if(yidong_pat._1.findFirstIn(s) != None){
                    return "该号段为--" + pre_number + "--的移动号码是靓号, 并且匹配的模式为: "  + yidong_pat._2
                }
            }

            // 如果遍历一遍没有返回值，说明该移动号码不是靓号,那么需要判断0到9的个数分别为多少
            for(num <- s){
                val temp = count_map(num.toString.toInt) + 1
                // 更新hashmap中的值
                count_map += (num.toString.toInt -> temp)
            }

            for(i <- 0 to 9){
                str += count_map(i).toString
            }
            return "该号段为--" + pre_number + "--的移动号码不是靓号, 0-9每个数字出现次数的结果为:" + str
        }

        // 判断是否是联通用户
        else if(liantong_user.contains(pre_number)){
            for(liantong_pat <- liantong_li){
                if(liantong_pat._1.findFirstIn(s) != None){
                    return "该号段为--" + pre_number + "--的联通号码是靓号，并且匹配的模式为: "+ liantong_pat._2
                }
            }

            //如果遍历一遍没有返回值, 说明该联通号码不是靓号, 那么需要判断0到9的个数分别为多少
            for(num <- s){
                val temp = count_map(num.toString.toInt) + 1
                // 更新hashmap中的值
                count_map += (num.toString.toInt -> temp)
            }

            for(i <- 0 to 9){
                str += count_map(i).toString
            }

            return "该号段为--" + pre_number + "--的联通号码不是靓号, 0-9每个数字出现次数的结果为:" + str
        }

        // 判断是否是电信用户
        else if(dianxin_user.contains(pre_number)){
            for(dianxin_pat <- dianxin_li){
                if(dianxin_pat._1.findFirstIn(s) != None){
                    return "该号段为--" + pre_number + "--的电信号码是靓号，并且匹配的模式为: "+ dianxin_pat._2
                }
            }

            //如果遍历一遍没有返回值, 说明该联通号码不是靓号, 那么需要判断0到9的个数分别为多少
            for(num <- s){
                val temp = count_map(num.toString.toInt) + 1
                // 更新hashmap中的值
                count_map += (num.toString.toInt -> temp)
            }

            for(i <- 0 to 9){
                str += count_map(i).toString
            }
            return "该号段为--" + pre_number + "--的电信号码不是靓号, 0-9每个数字出现次数的结果为:" + str
        }

        else{
            return "该用户号码不属于移动，联通或者电信号码, 因此无法判断该号码是否为靓号"
        }
    }

    def main(args: Array[String]) {
        import scala.io.StdIn
        println("请输入您的手机号码")
        val number:String = StdIn.readLine()
        val res = isInGoodNumber(number)
        println(res)
   }
}
