using System;
using System.Collections.Generic;
using System.Text;
using System.Xml.Serialization;

namespace LINQ
{
    [XmlType("car")]
    public class Car
    {
        public string model;
        public int year;

        [XmlElement(ElementName = "engine")]
        public Engine motor;

        public Car() { }

        public Car(string model, Engine motor, int year)
        {
            this.model = model;
            this.motor = motor;
            this.year = year;
        }
    }
}
