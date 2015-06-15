class person:
    """ a base person class """
    def __init__(self, n):
        self.full_name = n
    def get_name(self):
        return self.full_name


class student(person):
    """A class representing a student """
    def __init__(self, n, roll_number):
        person.__init__(n)
        self.roll_number = roll_number

    def get_roll_number(self):
        return self.roll_number

class Employee:
    'Common base class for all employees'
    empCount = 0

    def __init__(self, name, salary):
        self.name = name
        self.salary = salary
        Employee.empCount += 1

    def displayCount(self):
        print "Total Employee %d" % Employee.empCount

    def displayEmployee(self):
        print "Name : ", self.name,  ", Salary: ", self.salary

"This would create first object of Employee class"
emp1 = Employee("Zara", 2000)

print "Total Employee %d" % Employee.empCount

student1 = student("ff",201)

print "Student Roll Number %d" % student1.get_roll_number()

student2 = student("ram", 202)
print "Student Name %s" % student1.get_name()
print "Student Roll Number %d" % student1.get_roll_number()

