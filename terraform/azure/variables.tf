variable "location" {
  type    = string
  description = "The Azure region"
  default = "ukwest"
}

variable "resource_group" {
  type        = string
  description = "The name of the resource group"
  default     = "kanji-azure-rg"

}

variable "image" {
  type    = string
  description = "The container image for the Azure Container App"
  default = "ghcr.io/seanrb93/kanji-flashcards:latest"
}

variable "subscription_id" {
  description = "Azure subscription ID"
  type        = string
}
