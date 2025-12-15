variable "location" {
  type        = string
  description = "The Azure region"
  default     = "ukwest"
}

variable "resource_group" {
  type        = string
  description = "The name of the resource group"
  default     = "kanji-azure-rg"
}

variable "image_repo" {
  type        = string
  description = "Repository for the Docker image"
}

variable "image_tag" {
  type        = string
  description = "Image tag"
}

variable "subscription_id" {
  description = "Azure subscription ID"
  type        = string
}