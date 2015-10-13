import boto
s3 = boto.connect_s3('AKIAI2KAU4M2D2UGHFFA','iXDM4X57XW2E9dnSioFwen9B7oyU8fhz1VtsIPx2')
bucket = s3.lookup('magpie-logs')
for key in bucket:
    print key.name

