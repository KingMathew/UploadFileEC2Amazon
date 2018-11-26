package sftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 *
 * @author javagists.com
 *
 */
public class UploadFileSFTP {

    public static void main(String[] args) throws Exception {

        JSch jsch = new JSch();
        String privateKey = "C:\\Users\\mateo\\Downloads\\bi-stock-phase-1.pem";
        jsch.addIdentity(privateKey);
        Session session = null;
        try {
            session = jsch.getSession("ubuntu", "ec2-52-14-57-240.us-east-2.compute.amazonaws.com", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.put("C:\\Users\\mateo\\Downloads\\unnamed.jpg", "..//unnamed.jpg");
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        }

    }

}
