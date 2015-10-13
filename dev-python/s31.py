import boto

def list_all_buckets(s3):
    buckets = s3.get_all_buckets()
    return buckets

def main():
    s3 = boto.connect_s3(
        aws_access_key_id="AKIAI2KAU4M2D2UGHFFA",
        aws_secret_access_key="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")
    buckets = list_all_buckets(s3)

    print buckets

main()
