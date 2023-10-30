package com.ms.tools.core.base.info;

import com.ms.tools.core.base.basic.Strings;

/**
 * 脱敏工具类
 *
 * @author ms
 */
public class DesensitizationUtils {

    /**
     * 复姓常量
     */
    private static final String[] COMPOUND_SURNAME = {
            "阿单",
            "阿跌",
            "阿贺",
            "阿会",
            "阿里",
            "阿仑",
            "阿罗",
            "阿热",
            "哀骀",
            "艾岁",
            "安迟",
            "安都",
            "安国",
            "安金",
            "安陵",
            "安平",
            "安期",
            "安丘",
            "安是",
            "安阳",
            "奥敦",
            "奥鲁",
            "奥屯",
            "阿史那",
            "巴公",
            "拔拔",
            "拔列",
            "拔略",
            "拔也",
            "把利",
            "罢敌",
            "白马",
            "白狄",
            "白公",
            "白侯",
            "白鹿",
            "白鸾",
            "白冥",
            "白男",
            "白象",
            "白亚",
            "白乙",
            "白石",
            "百里",
            "柏常",
            "柏侯",
            "柏高",
            "班丘",
            "阪泉",
            "阪上",
            "鲍丘",
            "鲍俎",
            "苞丘",
            "卑梁",
            "卑徐",
            "北方",
            "北宫",
            "北郭",
            "北海",
            "北旄",
            "北门",
            "北比",
            "北丘",
            "北人",
            "北唐",
            "北堂",
            "北乡",
            "北殷",
            "北野",
            "北城",
            "北关",
            "北辰",
            "北山",
            "倍俟",
            "奔水",
            "逼阳",
            "比丘",
            "比人",
            "闭珊",
            "辟闾",
            "宾牟",
            "并官",
            "波斯",
            "拨略",
            "薄奚",
            "薄野",
            "伯比",
            "伯夫",
            "伯常",
            "伯成",
            "伯德",
            "伯封",
            "伯丰",
            "伯高",
            "伯昏",
            "伯暋",
            "伯夏",
            "伯有",
            "伯州",
            "伯宗",
            "不第",
            "不戴",
            "驳马",
            "薄姑",
            "薄奚",
            "薄野",
            "卜成",
            "卜梁",
            "卜马",
            "步叔",
            "步扬",
            "步温",
            "池张",
            "陈一",
            "曹牟",
            "曹丘",
            "常涛",
            "长鱼",
            "车非",
            "成功",
            "成公",
            "成阳",
            "乘马",
            "叱卢",
            "丑门",
            "樗里",
            "穿封",
            "淳于",
            "單于",
            "答禄",
            "达勃",
            "达步",
            "达奚",
            "登徒",
            "邓陵",
            "第一",
            "第二",
            "第三",
            "第四",
            "第五",
            "第六",
            "第七",
            "第八",
            "地连",
            "地伦",
            "东方",
            "东里",
            "东南",
            "东宫",
            "东门",
            "东乡",
            "东丹",
            "东郭",
            "东陵",
            "东关",
            "东闾",
            "东阳",
            "东野",
            "东莱",
            "豆卢",
            "斗于",
            "都尉",
            "独孤",
            "端木",
            "段干",
            "多子",
            "尔朱",
            "方雷",
            "丰将",
            "封人",
            "封父",
            "夫蒙",
            "夫余",
            "浮丘",
            "富察",
            "傅其",
            "傅余",
            "棼冒",
            "蚡冒",
            "范姜",
            "干已",
            "高车",
            "高陵",
            "高堂",
            "高阳",
            "高辛",
            "皋落",
            "哥舒",
            "盖楼",
            "庚桑",
            "梗阳",
            "宫孙",
            "公羊",
            "公良",
            "公孙",
            "公罔",
            "公西",
            "公冶",
            "公敛",
            "公梁",
            "公输",
            "公上",
            "公山",
            "公户",
            "公玉",
            "公仪",
            "公仲",
            "公甲",
            "公坚",
            "公宾",
            "公伯",
            "公祖",
            "公乘",
            "公晰",
            "公族",
            "姑布",
            "古口",
            "古龙",
            "古赖",
            "古孙",
            "穀梁",
            "谷浑",
            "瓜田",
            "关龙",
            "鲑阳",
            "归海",
            "虢射",
            "函治",
            "韩余",
            "罕井",
            "浩生",
            "浩星",
            "纥骨",
            "纥奚",
            "纥于",
            "贺陈",
            "贺拨",
            "贺兰",
            "贺楼",
            "赫连",
            "赫王",
            "黑齿",
            "黑肱",
            "侯冈",
            "呼延",
            "壶丘",
            "呼衍",
            "斛律",
            "胡非",
            "胡母",
            "胡毋",
            "胡林",
            "忽仑",
            "皇甫",
            "皇父",
            "花裳",
            "火拔",
            "胡桃",
            "兀官",
            "吉白",
            "即墨",
            "季夙",
            "季瓜",
            "季连",
            "季融",
            "季孙",
            "季尹",
            "茄众",
            "蒋丘",
            "姜匀",
            "金齿",
            "晋楚",
            "京城",
            "经孙",
            "泾阳",
            "九百",
            "九方",
            "九吾",
            "睢鸠",
            "沮渠",
            "巨母",
            "勘阻",
            "渴侯",
            "渴单",
            "可汗",
            "空桐",
            "崆峒",
            "空桑",
            "空相",
            "昆吾",
            "老阳",
            "郎佳",
            "乐羊",
            "荔菲",
            "栎阳",
            "梁丘",
            "梁由",
            "梁余",
            "梁垣",
            "陵阳",
            "伶舟",
            "冷沦",
            "令狐",
            "柳下",
            "龙丘",
            "龙藤",
            "卢妃",
            "卢蒲",
            "鲁步",
            "甪里",
            "陆费",
            "闾丘",
            "禄阁",
            "马矢",
            "马师",
            "麦丘",
            "麦卢",
            "茅夷",
            "蒙山",
            "孟孙",
            "弥牟",
            "密革",
            "密茅",
            "墨夷",
            "墨台",
            "万俊",
            "昌顿",
            "慕容",
            "木门",
            "木易",
            "万俟",
            "孟玄",
            "纳喇",
            "那拉",
            "纳兰",
            "南宫",
            "南郭",
            "南门",
            "南荣",
            "南离",
            "宁李",
            "欧侯",
            "欧阳",
            "逄门",
            "盆成",
            "彭祖",
            "平陵",
            "平宁",
            "破丑",
            "仆固",
            "濮阳",
            "浦思",
            "漆雕",
            "奇介",
            "綦母",
            "綦毋",
            "綦连",
            "祁连",
            "乞伏",
            "绮里",
            "千代",
            "千乘",
            "勤宿",
            "青阳",
            "丘丽",
            "丘陵",
            "曲沃",
            "屈侯",
            "屈突",
            "屈男",
            "屈卢",
            "屈同",
            "屈门",
            "屈引",
            "七七",
            "壤驷",
            "扰龙",
            "容成",
            "汝嫣",
            "萨孤",
            "三饭",
            "三闾",
            "三州",
            "桑丘",
            "商瞿",
            "上官",
            "尚方",
            "少师",
            "少施",
            "少室",
            "少叔",
            "少正",
            "社南",
            "社北",
            "申屠",
            "申徒",
            "沈犹",
            "神农",
            "胜屠",
            "石作",
            "石雨",
            "石牛",
            "侍其",
            "士季",
            "士弱",
            "士孙",
            "士贞",
            "叔敖",
            "叔梁",
            "叔孙",
            "叔先",
            "叔促",
            "水丘",
            "司城",
            "司空",
            "司寇",
            "司鸿",
            "司马",
            "司徒",
            "司士",
            "似和",
            "素和",
            "素黎",
            "夙沙",
            "孙阳",
            "索阳",
            "索卢",
            "沈江",
            "沓卢",
            "太史",
            "太叔",
            "太阳",
            "淡台",
            "唐山",
            "堂溪",
            "陶丘",
            "同蹄",
            "统奚",
            "秃发",
            "涂钦",
            "屠岸",
            "吐火",
            "吐贺",
            "吐万",
            "吐罗",
            "吐缶",
            "吐难",
            "吐缶",
            "吐浑",
            "吐奚",
            "吐和",
            "屯浑",
            "脱脱",
            "秃发",
            "拓拨",
            "拓跋",
            "澹台",
            "谭刘",
            "太宰",
            "完颜",
            "王孙",
            "王官",
            "王人",
            "王刘",
            "王子",
            "微生",
            "尾勺",
            "温孤",
            "温稽",
            "闻人",
            "屋户",
            "巫马",
            "巫许",
            "吾丘",
            "无庸",
            "无钩",
            "无终",
            "五鹿",
            "五鸠",
            "武安",
            "吴刘",
            "王黄",
            "息夫",
            "西陵",
            "西乞",
            "西钥",
            "西乡",
            "西门",
            "西周",
            "西郭",
            "西方",
            "西野",
            "西宫",
            "戏阳",
            "瑕吕",
            "霞露",
            "夏侯",
            "鲜虞",
            "鲜于",
            "鲜阳",
            "咸丘",
            "相里",
            "解枇",
            "谢丘",
            "新垣",
            "辛垣",
            "信都",
            "信平",
            "修鱼",
            "徐辜",
            "徐吾",
            "徐藤",
            "徐离",
            "宣于",
            "轩辕",
            "轩丘",
            "阏氏",
            "延陵",
            "罔法",
            "铅陵",
            "羊角",
            "耶律",
            "叶阳",
            "伊祁",
            "伊耆",
            "猗卢",
            "义渠",
            "邑由",
            "意如",
            "因孙",
            "银齿",
            "尹文",
            "雍门",
            "游水",
            "由吾",
            "右师",
            "有莘",
            "宥连",
            "于陵",
            "虞丘",
            "盂丘",
            "宇文",
            "尉迟",
            "乐羊",
            "乐正",
            "运龙",
            "运期",
            "宰父",
            "辗迟",
            "湛卢",
            "臧孙",
            "章仇",
            "仉督",
            "长孙",
            "长儿",
            "张廖",
            "张简",
            "真鄂",
            "正令",
            "执头",
            "中央",
            "中长",
            "中行",
            "中野",
            "中英",
            "中梁",
            "中垒",
            "钟离",
            "钟吾",
            "终黎",
            "终葵",
            "仲孙",
            "仲长",
            "周阳",
            "周氏",
            "周生",
            "朱阳",
            "诸葛",
            "主父",
            "颛孙",
            "颛顼",
            "訾辱",
            "淄丘",
            "子言",
            "子人",
            "子服",
            "子家",
            "子桑",
            "子南",
            "子叔",
            "子车",
            "子阳",
            "宗伯",
            "宗正",
            "宗政",
            "尊卢",
            "昨和",
            "左人",
            "左丘",
            "左师",
            "左行",
            "佐南"
    };

    /**
     * 手机号脱敏
     * <p>
     * 脱敏规则：前三位明文，后四位明文，其他脱敏
     * 标准中国大陆手机号
     * 11位 138 8888 8888
     * 含区号 +86 138 8888 8888
     * 含国家代码 086 138 8888 8888
     *
     * @param phone 手机号
     */
    public static String phoneDesensitization(String phone) {
        if (phone == null || phone.length() < 11) {
            return phone;
        }
        if (phone.startsWith("+86")) {
            phone = phone.substring(3);
        } else if (phone.startsWith("086")) {
            phone = phone.substring(3);
        } else if (phone.startsWith("86")) {
            phone = phone.substring(2);
        }
        phone = phone.replaceAll(" ", "");
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 邮箱脱敏
     * <p>
     * 脱敏规则：@前面的第一个字符明文，@后面的第一个字符明文，其他脱敏
     *
     * @param email 邮箱
     */
    public static String emailDesensitization(String email) {
        if (Strings.isBlank(email)) {
            return email;
        }
        int atIndex = email.indexOf("@");
        if (atIndex > 0) {
            String prefix = email.substring(0, atIndex);
            String maskedPrefix = 3 < prefix.length() ?
                    prefix.substring(0, 3) + "****" :
                    prefix;
            String domain = email.substring(atIndex);
            return maskedPrefix + domain;
        }
        return email;
    }

    /**
     * 邮箱脱敏
     * <p>
     * 脱敏规则：@前面的x字符明文，@后面的第一个字符明文，其他脱敏
     *
     * @param email        邮箱
     * @param prefixLength 保留明文长度
     */
    public static String emailDesensitization(String email, int prefixLength) {
        if (Strings.isBlank(email)) {
            return email;
        }
        int atIndex = email.indexOf("@");
        if (atIndex > 0) {
            String prefix = email.substring(0, atIndex);
            String maskedPrefix = prefixLength < prefix.length() ?
                    prefix.substring(0, prefixLength) + "****" :
                    prefix;
            String domain = email.substring(atIndex);
            return maskedPrefix + domain;
        }
        return email;
    }


    /**
     * 身份证号脱敏
     * <p>
     * 脱敏规则：前四位明文，后四位明文，其他脱敏
     *
     * @param idCard 身份证号
     */
    public static String idCardDesensitization(String idCard) {
        if (idCard == null || idCard.length() < 8) {
            return idCard;
        }
        return idCard.replaceAll("(\\w{4})\\w*(\\w{4})", "$1****$2");
    }

    /**
     * 银行卡号脱敏
     * <p>
     * 脱敏规则：前四位明文，后四位明文，其他脱敏
     *
     * @param bankCard 银行卡号
     */
    public static String bankCardDesensitization(String bankCard) {
        if (bankCard == null || bankCard.length() < 8) {
            return bankCard;
        }
        return bankCard.replaceAll("(\\w{4})\\w*(\\w{4})", "$1****$2");
    }

    /**
     * 姓名脱敏(中文)
     * <p>
     * 脱敏规则：
     * 2个字的 李四 -> 李*
     * 3个字及以上的的 李大四 -> 李*四  李静安寺四 -> 李*四
     * <p>
     * 特殊情况：·
     * 例如：李·四 -> 李*
     * 李阿松大·大阿松大·啊实打实四 -> 李·**四
     *
     * @param name 脱敏姓名
     */
    public static String nameDesensitization(String name) {
        if (name == null || name.length() < 2) {
            return name;
        }
        StringBuilder desensitizedName = new StringBuilder();

        // 是否为复姓
        boolean isDoubleSurname = false;
        for (String surname : COMPOUND_SURNAME) {
            if (name.startsWith(surname)) {
                isDoubleSurname = true;
                break;
            }
        }

        // 姓名中间的·
        int middlePoint = name.indexOf("·");
        if (middlePoint > 0) {
            // 截取 ·最后出现 到 结尾的字符串
            String afterMiddlePoint = name.substring(name.lastIndexOf("·") + 1);
            // 截取 最后两个字符(不足两个字符的话，就是本身)
            String lastTwoChar = afterMiddlePoint.substring(afterMiddlePoint.length() - 2);
            if (isDoubleSurname) {
                desensitizedName.append(name, 0, 2).append("·**").append(lastTwoChar);
            } else {
                desensitizedName.append(name, 0, 1).append("·**").append(lastTwoChar);
            }
        } else {
            // 姓名长度
            int nameLength = name.length();
            // 姓名最后一个字符
            String lastTwoChar = name.substring(nameLength - 1);
            if (nameLength == 2) {
                desensitizedName.append(name, 0, 1).append("*");
            } else {
                if (isDoubleSurname) {
                    desensitizedName.append(name, 0, 2).append("*").append(lastTwoChar);
                } else {
                    desensitizedName.append(name, 0, 1).append("*").append(lastTwoChar);
                }
            }
        }
        return desensitizedName.toString();
    }


    /**
     * 车牌号脱敏
     * <p>
     * 脱敏规则：前两位明文，后两位明文，其他脱敏
     *
     * @param carNo 车牌号
     */
    public static String carNoDesensitization(String carNo) {
        if (carNo == null || carNo.length() < 4) {
            return carNo;
        }
        return carNo.replaceAll("(\\w{2})\\w*(\\w{2})", "$1****$2");
    }

    /**
     * 车架号脱敏
     * <p>
     * 脱敏规则：前四位明文，后四位明文，其他脱敏
     *
     * @param carFrameNo 车架号
     */
    public static String carFrameNoDesensitization(String carFrameNo) {
        if (carFrameNo == null || carFrameNo.length() < 8) {
            return carFrameNo;
        }
        return carFrameNo.replaceAll("(\\w{4})\\w*(\\w{4})", "$1****$2");
    }

    /**
     * 发动机号脱敏
     * <p>
     * 脱敏规则：前四位明文，后四位明文，其他脱敏
     *
     * @param carEngineNo 发动机号
     */
    public static String carEngineNoDesensitization(String carEngineNo) {
        if (carEngineNo == null || carEngineNo.length() < 8) {
            return carEngineNo;
        }
        return carEngineNo.replaceAll("(\\w{4})\\w*(\\w{4})", "$1****$2");
    }
}
