variable "aws_region" {
  default = "eu-west-1"
}

variable "image_repo" {
  description = "Repository for the Docker image"
}

variable "image_tag" {
  default = "latest"
}

variable "service_name" {
  default = "kanji-flashcards"
}
