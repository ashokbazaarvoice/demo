from boto.s3.connection import S3Connection

conn = S3Connection('AKIAI2KAU4M2D2UGHFFA','iXDM4X57XW2E9dnSioFwen9B7oyU8fhz1VtsIPx2')
bucket = conn.get_bucket('magpie-logs')
for key in bucket.list():
    print key.name.encode('utf-8')
