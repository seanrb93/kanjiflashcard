variable "aws_region" {
  default = "eu-west-1"
}

variable "bucket_name" {
  description = "Name of the S3 bucket for Terraform state"
}

variable "dynamodb_table" {
  description = "Name of the DynamoDB table for Terraform locks"
}
