from boto.s3.connection import S3Connection

AWS_KEY = 'AKIAI2KAU4M2D2UGHFFA'
AWS_SECRET = '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'
aws_connection = S3Connection(AWS_KEY, AWS_SECRET)
bucket = aws_connection.get_bucket('magpie-logs')
for file_key in bucket.list():
    print file_key.name
