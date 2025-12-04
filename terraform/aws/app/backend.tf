terraform {
  backend "s3" {
    bucket         = "kanji-tf-state-srb"
    key            = "aws/app.tfstate"
    region         = "eu-west-1"
    dynamodb_table = "terraform-locks"
    encrypt        = true
  }
}
