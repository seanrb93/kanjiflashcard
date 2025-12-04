variable "location" {
  type    = string
  default = "westeurope"
}

variable "image_repo" {
  type = string
}

variable "image_tag" {
  type    = string
  default = "latest"
}

variable "resource_group_name" {
  type = string
}
