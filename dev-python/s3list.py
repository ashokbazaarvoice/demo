import boto
#boto.config.load_from_path('/Users/ashok.agarwal/.boto')
#s3 = boto.connect_s3('AKIAI2KAU4M2D2UGHFFA', 'iXDM4X57XW2E9dnSioFwen9B7oyU8fhz1VtsIPx2')
#s3 = boto.connect_s3(aws_access_key_id='AKIAI2KAU4M2D2UGHFFA', aws_secret_access_key='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')
s3 = boto.connect_s3()
bucket = s3.get_bucket('magpie-logs-test')
for key in bucket:
    print key.name
#buckets = s3.get_all_buckets()
#for key in buckets:
#    print key.name
