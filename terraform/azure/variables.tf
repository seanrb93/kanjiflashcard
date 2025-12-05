variable "location" {
  type    = string
  default = "ukwest"
}

variable "resource_group" {
  type    = string
  default = "kanji-azure-rg"
}

variable "image" {
  type    = string
  default = "ghcr.io/seanrb93/kanji-flashcards:latest"
}


