variable "aws_region" {
  type    = string
  default = "eu-west-1"
}

variable "image_repo" {
  type = string
}

variable "image_tag" {
  type    = string
  default = "latest"
}
