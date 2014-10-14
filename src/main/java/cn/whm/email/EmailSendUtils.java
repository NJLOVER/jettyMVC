package cn.whm.email;

import cn.whm.utils.ConfigUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wanghm on 2014/10/14.
 */
public class EmailSendUtils {
    private static Logger logger = LoggerFactory.getLogger(EmailSendUtils.class);

    private static String SMTP_HOST;
    private static int SMTP_PORT;
    private static String USERNAME;
    private static String PASSWORD;
    private static String EMAIL;
    private static String NICKNAME;
    private static int THREADCOUNT;
    private ExecutorService executorService;
    static{
        SMTP_HOST = ConfigUtils.getString("mail.smtp.host");
        SMTP_PORT = ConfigUtils.getInt("mail.smtp.port");
        USERNAME = ConfigUtils.getString("mail.username");
        PASSWORD = ConfigUtils.getString("mail.password");
        EMAIL = ConfigUtils.getString("mail.email");
        NICKNAME = ConfigUtils.getString("mail.nickname");
        THREADCOUNT = ConfigUtils.getInt("mail.threadCount");
        logger.info("loaded email config");
    }

    public void init(){
        executorService = Executors.newFixedThreadPool(THREADCOUNT);
    }

    private HtmlEmail getEmail(){
        HtmlEmail email = new HtmlEmail();
        defaultEmailConfig(email);
        return email;
    }

    private void defaultEmailConfig(Email email){
        try{
            email.setHostName(SMTP_HOST);
            email.setCharset("UTF-8");
            email.setSmtpPort(SMTP_PORT);
            email.setAuthentication(USERNAME,PASSWORD);
            email.setFrom(EMAIL,NICKNAME);
        }catch (Exception e){
            logger.error("Loading Mail properties error!",e);
        }
    }

    public boolean sendMail(String subject, String msg, String toList) {
        return sendMail(subject, msg, toList, null, null);
    }

    public boolean sendMail(String subject, String msg, String toList, String ccList) {
        return sendMail(subject, msg, toList, ccList, null);
    }

    public boolean sendMail(String subject, String msg, String toList, String ccList, String bccList) {
        return sendMail(subject, msg, splitList(toList), splitList(ccList), splitList(bccList));
    }

    public boolean sendMail(final String subject, final String msg, final List<String> toMails,
                            final List<String> ccMails, final List<String> bccMails) {
        boolean flag = false;
        if (StringUtils.isEmpty(subject) || CollectionUtils.isEmpty(toMails)) {
            return flag;
        }
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    HtmlEmail email =getEmail();
                    email.setMsg(msg);
                    email.setSubject(subject);
                    for(String toMail : toMails){
                        if(EmailValidator.getInstance().isValid(toMail)){
                            email.addTo(toMail);
                        }
                    }
                    if(CollectionUtils.isNotEmpty(ccMails)){
                        for(String ccMail : ccMails){
                            email.addCc(ccMail);
                        }
                    }
                    if(CollectionUtils.isNotEmpty(bccMails)){
                        for(String bccMail :bccMails){
                            email.addBcc(bccMail);
                        }
                    }
                    String result = email.send();
                    logger.info("Send mail sucess! result :{}",result);
                }catch (Exception e){
                    logger.error("Send mail error: ", e);
                }
            }
        });
        return true;
    }

    public boolean sendMultiPartEmail(final String subject, final String msg, final List<String> toMails,
                                      final List<String> ccMails, final List<String> bccMails, final List<File> files){
        boolean flag = false;
        if (StringUtils.isEmpty(subject) || CollectionUtils.isEmpty(toMails)) {
            return flag;
        }
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    HtmlEmail email =getEmail();
                    email.setMsg(msg);
                    email.setSubject(subject);
                    for(String toMail : toMails){
                        if(EmailValidator.getInstance().isValid(toMail)){
                            email.addTo(toMail);
                        }
                    }
                    if(CollectionUtils.isNotEmpty(ccMails)){
                        for(String ccMail : ccMails){
                            email.addCc(ccMail);
                        }
                    }
                    if(CollectionUtils.isNotEmpty(bccMails)){
                        for(String bccMail :bccMails){
                            email.addBcc(bccMail);
                        }
                    }
                    for (File file : files) {
                        email.attach(file);
                    }
                    String result = email.send();
                    logger.info("Send mail sucess! result :{}",result);
                }catch (Exception e){
                    logger.error("Send mail error: ", e);
                }
            }
        });
        return true;
    }

    private List<String> splitList(String mailString) {

        List<String> mailList = new ArrayList<String>();
        if (StringUtils.isNotBlank(mailString)) {
            String[] splits = mailString.split(";");
            if (ArrayUtils.isNotEmpty(splits)) {
                mailList = new ArrayList<String>(splits.length);
                for (String mail : splits) {
                    mailList.add(mail);
                }
            }
        }
        return mailList;
    }
}
