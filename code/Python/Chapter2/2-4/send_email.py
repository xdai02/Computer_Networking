import smtplib
from email.mime.text import MIMEText
from email.header import Header

# QQ邮箱SMTP服务器配置
smtp_server = 'smtp.qq.com'
smtp_port = 465  # SSL port
sender_email = '2513276112@qq.com'
sender_password = 'xxxxxxx'  # 授权码
receiver_email = '2513276112@qq.com'

# 邮件内容
subject = 'Test Email'          # 邮件标题
body = 'This is a test email sent using Python.'    # 邮件正文

# 邮件消息对象
message = MIMEText(body, 'plain', 'utf-8')
message['From'] = Header(sender_email)
message['To'] = Header(receiver_email)
message['Subject'] = Header(subject)

# 发送邮件
try:
    with smtplib.SMTP_SSL(smtp_server, smtp_port) as server:
        server.login(sender_email, sender_password)
        server.sendmail(sender_email, receiver_email, message.as_string())
    print("Email sent successfully!")
except Exception as e:
    print(f"Failed to send email: {e}")
