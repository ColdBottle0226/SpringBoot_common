package com.project.single.common.util;

import com.project.single.common.annotation.EncryptField;
import com.project.single.common.annotation.MaskingField;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.stereotype.Component;

import javax.crypto.IllegalBlockSizeException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Masking 처리 Util
 */
@Slf4j
@Component
public class MaskingUtil {

    public static <T> T maskingFieldProcess(T paramsObject) throws Exception {
        Class<? extends Object> resultClass = paramsObject.getClass();
        List<Field> declaredFieldList = FieldUtils.getAllFieldsList(resultClass);
        for (Field field : declaredFieldList) {
            EncryptField encryptField = field.getAnnotation(EncryptField.class);
            MaskingField maskField = field.getAnnotation(MaskingField.class);
            if (!Objects.isNull(encryptField)) {
                encryptField(field, paramsObject);
            }
            if (!Objects.isNull(maskField)) {
                maskingField(field, paramsObject);
            }
        }
        return paramsObject;
    }

    private static <T> void encryptField(Field field, T paramsObject) throws Exception {
        field.setAccessible(true);
        Object object = field.get(paramsObject);
        if (object instanceof String) {
            String value = (String) object;
            EncryptUtil encryptUtil = new EncryptUtil();
            try {
                field.set(paramsObject, encryptUtil.decrypt(value));
            } catch (IllegalBlockSizeException e) {
                field.set(paramsObject, encryptUtil.encrypt(value));
            } catch (IllegalArgumentException e) {
                field.set(paramsObject, encryptUtil.encrypt(value));
            } catch (Exception e) {
                field.set(paramsObject, null);
            }
        }
    }

    /**
     * methodName : maskingField
     * author :
     * description : Interceptor를 통해 Target Field를 전달 받아 Field 타입에 따라 Masking 처리한다.
     *
     * @param paramsObject
     */
    private static <T> void maskingField(Field field, T paramsObject) throws IllegalAccessException {
        field.setAccessible(true);
        Object object = field.get(paramsObject);
        if (object instanceof String) {
            String value = (String) object;
            switch (field.getAnnotation(MaskingField.class).value()) {
                // 아이디
                case ID:
                    field.set(paramsObject, getIdMasking(value));
                    break;
                // 이름
                case NAME:
                    field.set(paramsObject, getNameMasking(value));
                    break;
                // 이름 영문
                case NAME_EN:
                    field.set(paramsObject, getNameEnMasking(value));
                    break;
                // 이메일
                case EMAIL:
                    field.set(paramsObject, getEmailMasking(value));
                    break;
                // 전화번호
                case PHONE_NUM:
                    field.set(paramsObject, getPhoneNumberMasking(value));
                    break;
                // 아이피
                case IP:
                    field.set(paramsObject, getIpNumberMasking(value));
                    break;
                // 주민등록번호 / 외국인등록번호
                case REGIST_NUM:
                    field.set(paramsObject, getRegistNumMasking(value));
                    break;
                // 운전면허번호
                case DRIVER_LICENSE:
                    field.set(paramsObject, getDriverLicenseMasking(value));
                    break;
                // 여권번호
                case PASSPORT_NUM:
                    field.set(paramsObject, getPassportNumMasking(value));
                    break;
                // 신용카드번호 / 현금영수증카드번호
                case CREDIT_CARD:
                    field.set(paramsObject, getCreditCardMasking(value));
                    break;
                // 개인통관고유번호
                case PERSON_CUST_CLR_NUM:
                    field.set(paramsObject, getPersCustClrNmMasking(value));
                    break;
                // 계좌번호
                case ACNT_NUM:
                    field.set(paramsObject, getAcntNumMasking(value));
                    break;
                // 상세주소
                case DTL_ADDR:
                    field.set(paramsObject, getDtlAddrMasking(value));
                    break;
                // 주소
                case ADDR:
                    field.set(paramsObject, getAddrMasking(value));
                    break;
                // CI / DI .add by byon.2023.07.07
                case CI_DI:
                    field.set(paramsObject, getCiDiMasking(value));
                    break;
                // 핀번호 .add by byon.2023.10.12
                case PIN_NUM:
                    field.set(paramsObject, getPinNumMasking(value));
                    break;
                // 전화번호/휴대폰번호(1칸).add by byon.2023.07.07
                case TEL_AREA_NO:
                    field.set(paramsObject, getTelAreaNoMasking(value));
                    break;
                case ID_NAME:
                    field.set(paramsObject, getIdNameMasking(value));
                default:
                    break;
            }
        }
    }

    /**
     * methodName : getIdMasking
     * author :
     * description : ID값을 Masking 처리한다.
     *
     * @param str
     */
    public static String getIdMasking(String str) {
        String regex = "^[a-zA-Z0-9]*$";

        Matcher matcher = Pattern.compile(regex).matcher(str);
        if (matcher.find()) {
            String target = str;
            int length = str.length();
            int mskLen = 0;

            if (length > 3) {
                mskLen = 3;
            } else if (length > 2) {
                mskLen = 2;
            } else if (length > 1) {
                mskLen = 1;
            }
            char[] c = new char[length - mskLen];
            Arrays.fill(c, '*');

            return str.replace(target, target.substring(0, mskLen) + String.valueOf(c));
        }
        return str;
    }

    /**
     * methodName : getNameMasking
     * author :
     * description : 이름을 Masking 처리한다.
     *
     * @param str
     */
    public static String getNameMasking(String str) {
        String regexKr = "^[가-힣]*$";
        String regexEn = "^[a-zA-Z]*$";
        String name = "";
        String paramStr = StringUtils.replace(str, " ", "");

        Matcher matcherKr = Pattern.compile(regexKr).matcher(paramStr);
        Matcher matcherEn = Pattern.compile(regexEn).matcher(paramStr);
        int length = paramStr.length();
        if (matcherKr.find()) {
            for (int i = 0; i < length; i++) {
                if (i == 0 || (i > 1 && i == (length - 1))) {
                    name += paramStr.charAt(i);
                } else {
                    name += "*";
                }
            }
        } else if (matcherEn.find()) {
            for (int i = 0; i < length; i++) {
                int mskLen = 0;

                if (length > 3) {
                    mskLen = 3;
                } else if (length > 2) {
                    mskLen = 2;
                } else if (length > 1) {
                    mskLen = 1;
                }
                if (i < mskLen) {
                    name += paramStr.charAt(i);
                } else {
                    name += "*";
                }
            }
        } else {
            name = paramStr;
        }
        return name;
    }

    /**
     * methodName : getNameEnMasking
     * author :
     * description : 이름(영문)을 Masking 처리한다.
     *
     * modigy by byon.2023.07.07.보안정책변경으로 인함
     *   ex: Hong***dong => Hong*******
     *
     * @param str
     */
    public static String getNameEnMasking(String str) {
        if (str.length() < 8) {
            return str;
        } else {
//			String target = str.substring(4, str.length() - 4);	//modify by byon
            String target = str.substring(4, str.length());

            int length = target.length();
            char[] c = new char[length];
            Arrays.fill(c, '*');
            str = str.replace(target, String.valueOf(c));
        }
        return str;
    }

    /**
     * methodName : getEmailMasking
     * author :
     * description : Email을 Masking 처리한다.
     *
     * @param str
     */
    public static String getEmailMasking(String str) {
        String regex = "^(.+)@(.+)$";

        Matcher matcher = Pattern.compile(regex).matcher(str);
        if (matcher.find()) {
            String target = matcher.group(1);
            int length = target.length();
            int mskLen = 0;

            if (length > 2) {
                mskLen = 2;
            } else if (length > 1) {
                mskLen = 1;
            }
            char[] c = new char[length - mskLen];
            Arrays.fill(c, '*');

            return str.replace(target + "@", target.substring(0, mskLen) + String.valueOf(c) + "@");
        }
        return str;
    }

    /**
     * methodName : getPhoneNumberMasking
     * author :
     * description : 전화번호를 Masking 처리한다.(유선전화번호,휴대폰번호)
     *
     * modigy by byon.2023.07.07.보안정책변경으로 인함
     *   ex: 010–****-1234 => 010–1234-****)
     *
     * @param str
     */
    public static String getPhoneNumberMasking(String str) {
        String regex = "(\\d{2,3})-?(\\d{3,4})-?(\\d{4})$";
        String phNo = "";

        Matcher matcher = Pattern.compile(regex).matcher(str);
        if (matcher.find()) {
            String areaNo = matcher.group(1);
            String targetSc = matcher.group(2); //modify by byon
            String target = matcher.group(3);
            if (areaNo.indexOf("02") > -1 && areaNo.length() > 2) {
                target = areaNo.substring(areaNo.length() - 1) + target;
            }
            int length = target.length();
            char[] c = new char[length];
            Arrays.fill(c, '*');

            StringBuffer sb = new StringBuffer();
            phNo = String.valueOf(sb.append(areaNo).append("-").append(targetSc).append("-").append(String.valueOf(c)));
            return phNo;
        }
        return str;
    }

    /**
     * methodName : getIpNumberMasking
     * author :
     * description : IP를 Masking 처리한다.
     *
     * @param ip
     */
    public static String getIpNumberMasking(String ip) {
        String returnIp = ip;

        if (ip != null && ip.contains(".")) {
            String[] temp = ip.split("\\.");
            if (temp.length > 1) {
                temp[2] = temp[2].replaceAll("[0-9]", "*"); // 정규식으로 전체를 * 로 변환
            }
            returnIp = String.join(".", temp);
        } else {
            returnIp = "";
        }
        return returnIp;
    }

    /**
     * methodName : getRegistNumMasking
     * author : byon
     * description : 주민등록번호를 Masking 처리한다.
     *
     * add by byon.2023.07.07 보안정책변경으로 인함
     *
     * @param str
     */
    public static String getRegistNumMasking(String str) {
        String regex = "(\\d{6})-?(\\d{7})";
        Matcher matcher = Pattern.compile(regex).matcher(str);

        if (matcher.find()) {
            String target = matcher.group(2);
            target = target.substring(1, target.length());

            int length = target.length();
            char[] c = new char[length];
            Arrays.fill(c, '*');
            str = str.replace(target, String.valueOf(c));
        }
        return str;
    }

    /**
     * methodName : getDriverLicense
     * author :
     * description : 운전면허 번호를 Masking 처리한다.
     *
     * add by byon.2023.07.07 보안정책변경으로 인함
     *
     * @param str
     */
    public static String getDriverLicenseMasking(String str) {
        String regex = "(\\d{2})-?(\\d{2})-?(\\d{6})-?(\\d{2})";
        Matcher matcher = Pattern.compile(regex).matcher(str);

        if (matcher.find()) {
            String target1 = matcher.group(3);
            String target2 = matcher.group(4);
            target1 = target1.substring(2, target1.length());

            int length1 = target1.length();
            int length2 = target2.length();
            char[] c1 = new char[length1];
            char[] c2 = new char[length2];
            Arrays.fill(c1, '*');
            Arrays.fill(c2, '*');
            str = str.replace(target1, String.valueOf(c1));
            str = str.replace(target2, String.valueOf(c2));
        }
        return str;
    }

    /**
     * methodName : getPassportNumMasking
     * author :
     * description : 여권번호를 Masking 처리한다.
     *
     * add by byon.2023.07.07 보안정책변경으로 인함
     *
     * @param str
     */
    public static String getPassportNumMasking(String str) {

        if (str.length() < 5) {
            return str;
        } else {
//			String target = str.substring(0, str.length() - 4);	//modify by byon.2023.07.07
            String target = str.substring(str.length() - 4, str.length());

            int length = target.length();
            char[] c = new char[length];
            Arrays.fill(c, '*');
            str = str.replace(target, String.valueOf(c));
        }
        return str;
    }

    /**
     * methodName : getCreditCardMasking
     * author :
     * description : 신용카드번호를 Masking 처리한다.
     *
     * add by byon.2023.07.07 보안정책변경으로 인함
     *
     * @param str
     */
    public static String getCreditCardMasking(String str) {

        String target = str.substring(4, 12);

        int length = target.length();
        char[] c = new char[length];
        Arrays.fill(c, '*');
        str = str.replace(target, String.valueOf(c));

        return str;

    }

    /**
     * methodName : getPersCustClrNmMasking
     * author :
     * description : 개인통관고유번호를 Masking 처리한다.
     *
     * add by byon.2023.07.07 보안정책변경으로 인함
     *
     * @param str
     */
    public static String getPersCustClrNmMasking(String str) {

        if (str.length() < 12) {
            return str;
        } else {
            String target = str.substring(3, 12);

            int length = target.length();
            char[] c = new char[length];
            Arrays.fill(c, '*');
            str = str.replace(target, String.valueOf(c));
        }
        return str;
    }

    /**
     * methodName : getAcntNumMasking
     * author :
     * description : 계좌번호를 Masking 처리한다.
     *
     * add by byon.2023.07.07 보안정책변경으로 인함
     *
     * @param str
     */
    public static String getAcntNumMasking(String str) {

        if (str.length() < 7) {
            return str;
        } else {
//			String target = str.substring(3, 7);	//modify by byon.2023.07.07
            String target = str.substring(str.length() - 5, str.length());

            int length = target.length();
            char[] c = new char[length];
            Arrays.fill(c, '*');
            str = str.replace(target, String.valueOf(c));
        }
        return str;
    }

    /**
     * methodName : getDtlAddrMasking
     * author :
     * description : 상세주소를 Masking 처리한다.
     *
     * @param str
     */
    public static String getDtlAddrMasking(String str) {

        int length = str.length();
        char[] c = new char[length];
        Arrays.fill(c, '*');
        str = str.replace(str, String.valueOf(c));
        return str;
    }

    /**
     * methodName : getAddrMasking
     * author :
     * description : 기본주소를 Masking 처리한다.
     *
     * @param str
     */
    public static String getAddrMasking(String str) {

        String regex = "(([가-힣]+(\\d{1,5}|\\d{1,5}(,|.)\\d{1,5}|)+(읍|면|동|가|리))(^구|)((\\d{1,5}(~|-)\\d{1,5}|\\d{1,5})(가|리|)|))([ ](산(\\d{1,5}(~|-)\\d{1,5}|\\d{1,5}))|)|";
        String newRegx = "(([가-힣]|(\\d{1,5}(~|-)\\d{1,5})|\\d{1,5})+(로|길))";

        Matcher matcher = Pattern.compile(regex).matcher(str);
        Matcher newMatcher = Pattern.compile(newRegx).matcher(str);
        if(matcher.find()) {
            str = str.replaceAll("[0-9]", "*");
        } else if(newMatcher.find()) {
            str = str.replaceAll("[0-9]", "*");
        }

        return str;
    }

    /**
     * methodName : getIdNameMasking
     * author :
     * description : ID(NAME)값을 Masking 처리한다.
     *
     * @param str
     */
    private static String getIdNameMasking(String str) {
        String[] temp = str.split("\\(");
        String maskedId = getIdMasking(temp[0].trim());

        StringBuilder sb = new StringBuilder();
        sb.append(maskedId);
        if (temp.length > 1) {
            sb.append(" ");
            String maskedName = getNameMasking(temp[1].substring(0, temp[1].length() - 1));
            sb.append("(");
            sb.append(maskedName);
            sb.append(")");
        }
        return sb.toString();
    }

    /**
     * methodName : getCiDiMasking
     * author :
     * description : CI/DI 를 Masking 처리한다.
     *
     * add by byon.2023.07.07 보안정책추가로 인함
     * 앞 7자리 제외 마스킹 처리(ex: yCP3v5d**** ~~ ****)
     *
     * @param str
     */
    public static String getCiDiMasking(String str) {
        if (str.length() < 8) {
            return str;
        } else {
            String target = str.substring(7, str.length());

            int length = target.length();
            char[] c = new char[length];
            Arrays.fill(c, '*');
            str = str.replace(target, String.valueOf(c));
        }
        return str;
    }

    /**
     * methodName : getPinNumMasking
     * author :
     * description : 핀번호를 Masking 처리한다.
     *
     * add by byon.2023.10.12 보안정책으로 인함
     * 예) ********9012
     * 2023.12.12 보안정책으로 변경 앞3자리,뒤4자리 제외한 나머지 마스킹처리
     * 예) 232*****9012
     * @param str
     */
    public static String getPinNumMasking(String str) {

        if (str.length() < 12) {
            return str;
        } else {
            String target = str.substring(3, 8);

            int length = target.length();
            char[] c = new char[length];
            Arrays.fill(c, '*');
            str = str.replace(target, String.valueOf(c));

        }
        return str;
    }

    /**
     * methodName : getCiDiMasking
     * author :
     * description : 전화번호/휴대폰번호(1칸) 를 Masking 처리한다.
     *
     * add by byon.2023.07.07 보안정책추가로 인함
     * 마지막 4자리 마스킹 처리(ex: 010–1234-****)
     *
     * @param str 전화번호/휴대폰번호
     */
    public static String getTelAreaNoMasking(String str) {

        if (str.length() < 9) {
            return str;
        } else {
            String strbase = str.substring(0, str.length() - 4);
            str = strbase + "****";
        }
        return str;
    }
}
