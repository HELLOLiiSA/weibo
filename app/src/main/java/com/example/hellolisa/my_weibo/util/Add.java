package com.example.hellolisa.my_weibo.util;

import com.example.hellolisa.my_weibo.R;
import com.example.hellolisa.my_weibo.db.pinglun;
import com.example.hellolisa.my_weibo.db.user;
import com.example.hellolisa.my_weibo.db.webook;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Random;

/**
 * Created by HELLOLISA on 2018/12/20.
 */
/*
* 初始化部分数据的类
* 可以添加用户，微博
* 也可以当没有感情的评论机器
*
* */
public class Add {
    public Add(){};

    String[] username = {"hellolisa", "王思聪","PlayStation","qiuqiu","Qyxorz","laluo","MRDC","zard","hanser","fkey",
                "ataru", "mr_quin","suerly","leonardo","xiyi","yingge","krl","ask","cherry","alex",
                "yinyin", "aili","onlylove","midboss","gameover","frog","aha","yoseno","c8jiang","430"};
    String[] passwore = {"123456", "wsc","PlayStation","qiuqiu","qyxorz","laluo","mrdc","zard","hanser","fkey",
            "ataru", "mr_quin","suerly","leonardo","xiyi","yingge","krl","ask","cherry","alex",
            "yinyin", "aili","onlylove","midboss","gameover","frog","aha","yoseno","c8jiang","430"};
    String[] sex = {"女", "男","女","男","女","男","女","女","男","女",
            "男", "女","男","女","男","女","女","男","女","男",
            "男", "女","男","女","男","女","男","女","男","女"};
    String[] birth = {"1997-11-17", "1988-1-3","1978-5-6","1996-2-1","1997-10-13","1996-3-5","1991-10-10","1997-10-4","1992-5-15","1962-3-12",
            "1997-11-17", "1988-1-3","1978-5-6","1996-2-1","1997-10-13","1996-3-5","1991-10-10","1997-10-4","1992-5-15","1962-3-12",
            "1997-11-17", "1988-1-3","1978-5-6","1996-2-1","1997-10-13","1996-3-5","1991-10-10","1997-10-4","1992-5-15","1962-3-12"};
    String[] address = {"浙江", "北京","上海","江苏","福建","浙江","上海","山东","福建","北京",
            "江苏", "重庆","上海","英国","重庆","浙江","日本","上海","上海","湖南",
            "广西", "上海","湖南","陕西","美国","unkown","新疆","日本","上海","湖南"};
    String[] self = {"not fun and not easy", "为人低调的网红小王","PlayStation","qiuqiunile","Qyxorz","sleepzzzz","happy","hugebaby","love life","nothing",
            "二文", "摸了","可还行","大家好","永远是我大哥","暴躁","rider kick","","keyboard","aaaaa",
            "嘤嘤嘤", "有梦共勉","OOOOOnly","说啥呢","结束了","呱","啊哈","yooooooooooooo","应当","lzzscl"};
    int[] img = {R.drawable.logo_s, R.drawable.ay2, R.drawable.hao2, R.drawable.gg2, R.drawable.gn2, R.drawable.logo_s, R.drawable.mao2, R.drawable.gn, R.drawable.lt2, R.drawable.nt2
          , R.drawable.nt2, R.drawable.logo_s, R.drawable.hao2, R.drawable.nt2, R.drawable.logo_s, R.drawable.logo_s, R.drawable.gg2, R.drawable.ay2, R.drawable.logo_s, R.drawable.mao2
         , R.drawable.lt2, R.drawable.lt2, R.drawable.logo_s, R.drawable.ay2, R.drawable.lt2, R.drawable.lt2, R.drawable.hao2, R.drawable.mao2, R.drawable.gg2, R.drawable.gn2};



    public void addtouser(){
        int i = 0;
        for( ; i < 30; i++) {
            user nuser = new user();
           nuser.setUser_head(img[i]);
            nuser.setUser_name(username[i]);
            nuser.setUser_password(passwore[i]);
            nuser.setUser_sex(sex[i]);
            nuser.setUser_birth(birth[i]);
            nuser.setUser_address(address[i]);
            nuser.setUser_self(self[i]);
            nuser.save();
        }
    }


    public String[] username2 = {"hellolisa", "王思聪","PlayStation","qiuqiu","Qyxorz","laluo","MRDC","zard","hanser","fkey",
            "hellolisa", "王思聪","PlayStation","qiuqiu","Qyxorz","laluo","MRDC","zard","hanser","fkey",
            "ataru", "mr_quin","suerly","leonardo","xiyi","yingge","krl","ask","cherry","alex",
            "yinyin", "aili","onlylove","midboss","gameover","frog","aha","yoseno","c8jiang","430",
            "hellolisa", "hellolisa","PlayStation","qiuqiu","hellolisa","laluo","hellolisa","aha","alex","hellolisa",};

    public String[] test = {"#吴奇隆妈妈证实刘诗诗怀孕# 吴奇隆与刘诗诗身边密友透露#曝刘诗诗怀孕# 5个多月，否认双胞胎。祝福@刘诗诗，坐等小公主/小王子的到来啦~~ ",
            "瑞士莲品牌宣传片甜蜜上线，一同感受瑞士莲巧克力大师与中国区品牌代言人@辛芷蕾 幕后的融情时刻，当你剥开LINDOR的包装，轻咬精致的巧克力外壳，难以抗拒的幼滑软心在口中慢慢融化，你便缓缓地陶醉在这融情时光中。",
            "锅老师翘起来的呆毛很可爱了。",
            "虽然没有雪，想必是冬天，打在房子上的光的色彩和斜度～",
            "已经老了，他没有助理，自己拿行李，敬佩！大智若愚，大爱无垠，这才是真正的艺术家。",
            "通过不停的单带拉扯，RNG再度拿下大龙将EDG场上十座防御塔摧毁锁定胜局。最终，RNG推上EDG高地将其四人斩杀，拿下比赛胜利，恭喜RNG！",
            "可爱善良的韬韬，期待东方卫视和你一起跨年！",
            "年底加班多，一直没什么时间认真给孩子给检查作业！ 看了娜姐推荐的@小猿口算，用了下超级方便。 拍照就能检查整页口算作业，竖式拖式都没问题，还能检查应用题！当妈的终于解脱了！",
            "外交部证实：加公民因非法就业被地方公安机关行政拘留",
            "淡极始知花更艳，盛家有女初长成。",
            "说实话，原来我觉得父母不容易，就还停留在表面上的那种理解，直到自己工作了出来打拼才发现，这也太不容易了两口子咋熬过来的。",
            "親愛的星星，不知不覺你就兩歲了，你現在越來越有主見，也越來越調皮了，希望你能夠學會分享，希望你能擁有機器貓的竹蜻蜓自由的飛翔，也希望你能就這樣永遠黏著我不放，生日快樂",
            "还在偷偷想，怎么有块雪花牛肉掉在这里，细瞅结果发现只是条淋湿的围巾......"
            ,"雨下整夜，主人的爱溢出就像雨水，",
            "女子抖音发城管巡街配乐“鬼子进村” 警方：侮辱执法人员，拘留5天",
            "办理过小鸣单车破产的律师黄治国表示，《破产法》明确规定，破产清算时按照先后顺序进行清偿，首先偿还的是破产清算费用、国家的税款、有担保的债权、员工的工资等，应该归还客户的钱是放在倒数第二位清偿顺位的。",
            "当地时间12月19日，加拿大总理特鲁多周三在新闻发布会上证实，有第三名加拿大公民在中国被拘。",
            "近日，河南焦作退伍老兵韦先生在家训练时，2岁女儿看见就有样学样。于是韦先生给女儿买了迷彩服和迷彩帽，拉她到野外军训，不仅要立正稍息敬个礼，还要正步匍匐爬爬坡，动作可爱，萌翻众人。",
            "者荣耀新英雄，上官婉儿。用毛笔的古风妹子，琴棋书画都要凑全了吧",
            "山东艺术学院学生曝料，最近校方不让快递车进校，取快递要到对面小区，步行过去需半小时，并吐槽：取个快递要跨越大山大河。",
            "如果辅助为了保护队友不停被对方打死最后被系统判定送人头扣分怎么办？",
            "先明确一下，无证据表明ofo的行为涉嫌犯罪。其关于押金问题所涉及的仅是合同纠纷等民事问题。而这个问题，小法曾经说过，提起民事诉讼耗时耗力且成本代价太高，不宜进行。",
            "媒体报道，越南一名保姆虐待90岁老人，视频曝光后引起公愤",
            "该图片是一个漫画人物的主角，身份是一个篮球队第6人",
            "美国一男子因为杀害怀孕的妻子和两个女儿被判刑",
            "某小学一年级的班主任出于好心，帮老家的一些亲戚朋友吆喝土特产，向班里的家长推销"
            ,"近日，安徽合肥。网约车司机龚师傅，每天上班会带着妻子",
            "#渔民在东海发现无人游轮#，发现者称可能是缆绳断了被风吹来的。",
            "#失独家庭诞下龙凤胎# 面对襁褓中一对试管龙凤胎，52岁的父亲孟现杰希望把两个孩子培养成“像他们哥哥那样的人”。",
            "#ofo退押金排队系统人数#，据报道已经到一千多万了，看来ofo凉凉已经是必然之事。戴威一封又一封的内部信也是无济于事，不可能挽狂澜于既倒，扶大厦于将倾。",
            "10日，河南郑州，一老人着急赶车，不小心把拿的包子和稀饭甩了出去。公交司机王新伟看到后，把自己还没来得及吃的包子给了老人。不想老人连等三天，遇到王新伟后，把一兜包子还给了他。",
            "很多考研的学生已经来到自习室开始学习了。一考研学生声音沙哑仍坚持大声背书，她说一天大概学习17个小时。另一位考研学生称，临近考试，每天心情要变几百次，常自我怀疑。",
            "在长沙809路公交车上，一女子#抱狗上车致全车人被迫下车#。",
            "河南栾川，网曝男子常某将初中老师张某拦在路上掌掴，自称因家穷被老师欺负，留下伤害。",
            "最高人民检察院印发第十二批指导性案例，均为正当防卫或者防卫过当的案件，昆山“反杀案”入选其中。",
            "武汉的刘先生停放在楼下正在充电的电动车被小偷看上了，小偷在偷电瓶时意外触电身亡。小偷家属向刘先生索赔20万赔偿金，且一分不能少。最后经法院调解，车主答应赔偿5万块钱的精神损失费。",
            "读书如果读出了优越感，还不如不读，哪怕你读的张爱玲……你觉得呢？ ????",
            "据中国执行信息公开网披露消息，12月4日，法院对东峡大通（北京）管理咨询有限公司作出了“限制消费令”，该公司及戴威本人不得坐飞机、软卧等，不能在星级宾馆等场合消费等。",
            "CNN消息，美国一名29岁的偷猎者David Berry因偷猎杀死了上百只鹿被捕。",
            "【韩国“素媛案”凶犯赵斗淳在狱中抄写圣经，“是一种故意制造好感的表现”】近日，韩国民众对于赵斗淳2年后即将出狱的愤怒一直没有平息。",
            "坏起来了",
            "别别别，使不得",
            "近日，江西宜春一女子跳湖轻生，叶传健、陈肯、张诚闻讯后，先后跳进冰冷的水里营救。女子被救起，而最先下水救人的叶传健却献出了仅24岁年轻的生命。",
            "有意思了，我一个人来看电影，前面妹子也是一个人，整个电影院就我们俩人。",
            "hellolisa"
            ,"江西南昌，一小伙逃出传销跳上公交车，司机罗勇了解情况后安抚他，“上了公交就安全了”。随后，司机把他带回车队并报警。",
            "欲言又止",
            "感情这东西是很难处理的，不能往冰箱里一搁，就以为它可以保存若干时日，不会变质了。—— 张爱玲 《半生缘》",
            "人生的美好就是每天醒来都能看到上帝赐我的那一米阳光","hellllllllllllllllllo"};

    public String[] sandtime = {"12：20：12：20", "12：20：12：20","12：20：12：20","12：20：12：20","12：20：12：20","12：20：12：20","12：20：12：20","12：20：12：20","12：20：12：20","12：20：12：20",
            "12：20：12：30", "12：20：12：30","12：20：12：30","12：20：12：30","12：20：12：30","12：20：12：30","12：20：12：30","12：20：12：30","12：20：12：30","12：20：12：30",
            "12：20：12：40", "12：20：12：40","12：20：12：40","12：20：12：40","12：20：12：40","12：20：12：40","12：20：12：40","12：20：12：40","12：40：12：40","12：40：12：40",
            "12：20：12：50", "12：20：12：50","12：20：12：50","12：20：12：50","12：20：12：50","12：20：12：50","12：20：12：50","12：20：12：50","12：20：12：50","12：20：12：50",
            "12：20：18：20", "12：20：19：20","12：20：20：20","12：20：21：20","12：20：22：20","12：20：23：20","12：20：24：20","12：21：1：20","12：21：2：20","12：21：03：13"};


   // public int[] img2 = {R.drawable.logo_s, R.drawable.ay2, R.drawable.hao2, R.drawable.gg2, R.drawable.gn2, R.drawable.logo_s, R.drawable.mao2, R.drawable.gn2, R.drawable.lt2, R.drawable.nt2,
    //        R.drawable.logo_s, R.drawable.ay2, R.drawable.hao2, R.drawable.gg2, R.drawable.gn2, R.drawable.logo_s, R.drawable.mao2, R.drawable.gn2, R.drawable.lt2, R.drawable.nt2
     //       , R.drawable.nt2, R.drawable.logo_s, R.drawable.hao2, R.drawable.nt2, R.drawable.logo_s, R.drawable.logo_s, R.drawable.gg2, R.drawable.ay2, R.drawable.logo_s, R.drawable.mao2
      //      , R.drawable.lt2, R.drawable.lt2, R.drawable.logo_s, R.drawable.ay2, R.drawable.lt2, R.drawable.lt2, R.drawable.hao, R.drawable.mao2, R.drawable.gg2, R.drawable.gn2,
       //     R.drawable.logo_s, R.drawable.logo_s, R.drawable.hao2, R.drawable.gg2, R.drawable.logo_s, R.drawable.logo_s, R.drawable.logo_s, R.drawable.gn2, R.drawable.lt2, R.drawable.logo_s};


    /*
    * 录入微博
    * */
    public void addtowebook(){
        int i = 0;
        for( ; i < 50; i++) {
            webook webook = new webook();
            //webook.setHead_img(img2[i]);
            webook.setUser_name(username2[i]);
            webook.setTest(test[i]);
            webook.setSend_time(sandtime[i]);
            int min=0;
            int max=40;
            Random random = new Random();
            int num = random.nextInt(max)%(max-min+1) + min;
            webook.setGood_num(num);
            webook.setZhuanfa_num(0);
            webook.setPinglun_num(0);
            webook.save();
        }
    }

/*
* 没有感情的评论机器
* */
    public void addpinglun(){
        List<webook> webooks = DataSupport.findAll(webook.class);
        for(webook webook:webooks)
        {
            pinglun pinglun = new pinglun();
            pinglun.setSend_time(DatetimeUtil.getNowDateTime());
            pinglun.setSend_user_name("alex");
            pinglun.setBesend_user_name(webook.getUser_name());
            pinglun.setBe_test(webook.getTest());
            pinglun.setTest("没有感情的评论机器：alex");

            List<webook> webooki = DataSupport.where("user_name = ? and test = ?" , webook.getUser_name(), webook.getTest()).find(webook.class);
            webooki.get(0).setPinglun_num(webooki.get(0).getPinglun_num() + 1);
            webooki.get(0).updateAll("user_name = ? and test = ?" , webook.getUser_name(), webook.getTest());
            pinglun.save();

        }
    }
}
