package com.customer

trait Customers extends Iterable[Customer] {
  def add(Customer: Customer)
  def find(name: String): Option[Customer]
  def findOrNull(name: String): Customer
}