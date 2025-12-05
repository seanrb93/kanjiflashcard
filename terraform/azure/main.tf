terraform {
    required_providers {
    azurerm = {
      source = "hashicorp/azurerm"
      version = "4.55.0"
    }
    }
    
    backend "azurerm" {
    resource_group_name  = "kanji-tf"
    storage_account_name = "kanjitfstate"
    container_name       = "tfstate"
    key                  = "azure/terraform.tfstate"
  }

  required_version = ">= 1.0.0"

}

provider "azurerm" {
  features {}
}

resource "azurerm_resource_group" "kanji_rg" {
  name     = var.resource_group
  location = var.location
}

resource "azurerm_container_app_environment" "kanji_env" {
  name                = "kanji-env"
  location            = azurerm_resource_group.kanji_rg.location
  resource_group_name = azurerm_resource_group.kanji_rg.name
}

resource "azurerm_container_app" "kanji" {
  name                         = "kanji-flashcards"
  resource_group_name          = azurerm_resource_group.rg.name
  container_app_environment_id = azurerm_container_app_environment.env.id

  revision_mode = "Single"

  template {
    container {
      name   = "kanji-flashcards"
      image  = var.image
      cpu    = 0.5
      memory = "1.0Gi"

      env {
        name  = "PORT"
        value = "8080"
      }
    }
  }

  ingress {
    external_enabled = true
    target_port      = 8080
    transport        = "auto"

    traffic_weight {
      percentage      = 100
      latest_revision = true
    }
  }
}

output "container_app_url" {
  value = azurerm_container_app.kanji.latest_revision_fqdn
}