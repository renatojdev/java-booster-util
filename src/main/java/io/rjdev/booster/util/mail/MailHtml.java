package io.rjdev.booster.util.mail;


import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import org.apache.commons.mail.HtmlEmail;

import io.rjdev.booster.util.Resource;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

import javax.activation.FileDataSource;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

@Getter @Setter
public class MailHtml {
    private String mailHost;
    private int smtpPort = 587;
    private String to;
    private String from;
    private String subject;
    private String body;
    private String userName;
    private String password;
    private String urlEmailServidor;
    private HashMap<String, String> imagemEmbed;
    private String attachment;
    private String attachName;
    private String attachContentType;
    private ByteArrayInputStream attachStream;


    public static void main(String[] args) {
        MailHtml mailHtml = new MailHtml();
        mailHtml.setMailHost(Resource.get("mailhost"));
        mailHtml.setUserName(Resource.get("username"));
        mailHtml.setPassword(Resource.get("password"));
        mailHtml.setFrom(Resource.get("from"));
        mailHtml.setTo("hello@world.com");
        mailHtml.setSubject("Teste");
        mailHtml.setAttachment("src/main/resources/200t.jpg");
        mailHtml.setBody("<p><strong>BODY HTML TEST</strong></p>");
        try {
            mailHtml.sendHtml();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendHtml() throws Exception {
        final HtmlEmail email = new HtmlEmail();
        try {
            email.setHostName(this.mailHost);
            email.setSmtpPort(this.smtpPort);
            if(this.smtpPort != 587)
                email.setSmtpPort(this.smtpPort);
            email.addTo(this.to);
            email.setFrom(this.from);
            email.setSubject(this.subject);
            if(this.imagemEmbed != null) {
                for (int i = 0; i < this.imagemEmbed.size(); ++i) {
                    final String urlImagem = this.imagemEmbed.get("@cid" + i);
                    final URL url = new URL(urlImagem);
                    final String cid = email.embed(url, "Imagem " + i);
                    this.body = this.body.replaceAll("@cid" + i, cid);
                }
            }
            email.setHtmlMsg(this.body);
            email.setTextMsg("Seu cliente de email nao suporta mensagens em Formato HTML. Para visualizar o conteudo deste email, acesse: " + this.urlEmailServidor);
            if (this.userName != null && this.password != null) {
                email.setAuthentication(this.userName, this.password);
            }
            if(this.attachment != null) {
                EmailAttachment emailAtt = new EmailAttachment();
                emailAtt.setPath(this.attachment);
                emailAtt.setDisposition(EmailAttachment.ATTACHMENT);
                // Criar um objeto FileDataSource para o arquivo a ser anexado
                FileDataSource fileDataSource = new FileDataSource(this.attachment);
                // Adicionar o anexo com tipo MIME ao email
                MimeMultipart mimeMultipart = new MimeMultipart(fileDataSource);
                email.setContent(mimeMultipart);
                email.attach(fileDataSource, getFileName(this.attachment), attachName);
            }
            // attach as stream
            if (attachStream != null && attachStream instanceof ByteArrayInputStream) {
                // adicionar anexo com stream
                email.attach(new ByteArrayDataSource(attachStream, attachContentType), attachName, null);
            }
            // send
            email.send();
        }
        catch (EmailException ee) {
            ee.printStackTrace(System.out);
            System.err.println(ee);
            throw new Exception((Throwable)ee);
        }
    }

    public String getExtenstion(String pathname){
        File file = new File(pathname);
        String filename = file.getName();
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex != -1) {
            String extension = filename.substring(lastDotIndex + 1);
            System.out.println("Extension: " + extension);
            return extension;
        } else {
            System.out.println("No extension found");
        }
        return null;
    }

    private String getFileName(String pathname){
        File file = new File(pathname);
        String filename = file.getName();
        System.out.println("Filename: " + filename);
        return filename;

    }

    public String getContentType(String filename) {
        if (filename.endsWith(".pdf")) {
            return "application/pdf";
        } else if (filename.endsWith(".jpg") ||
            filename.endsWith(".jpeg")) {
            return "image/jpg";
        } else if (filename.endsWith(".png")) {
            return "image/png";
        } else if (filename.endsWith(".gif")) {
            return "image/gif";
        } else if (filename.endsWith(".bmp")) {
            return "image/bmp";
        } else {
            return "application/octet-stream";
        }
    }



}