#include <iostream>
#include <typeinfo>
using namespace std;

class Flyable                       // 能飞的
{
public:
    virtual void takeoff() = 0;     // 起飞
    virtual void land() = 0;        // 降落
};
class Bird : public Flyable         // 鸟
{
public:
    void foraging() {
        cout << "foraging for a bird" << endl;
    }           // 觅食
    void takeoff() override {
        cout << "bird taking off" << endl;
    }
    void land() override {
        cout << "bird landing" << endl;
    }
    virtual ~Bird(){}
};
class Plane : public Flyable        // 飞机
{
public:
    void carry() {
        cout << "plane carrying" << endl;
    }              // 运输
    void takeoff() override {
        cout << "plane taking off" << endl;
    }
    void land() override {
        cout << "bird landing" << endl;
    }
};

void doSomething(Flyable *obj)                 // 做些事情
{
    obj->takeoff();

    cout << typeid(*obj).name() << endl;        // 输出传入对象类型（"class Bird" or "class Plane"）

    if(typeid(*obj) == typeid(Bird))            // 判断对象类型
    {
        Bird *bird = dynamic_cast<Bird *>(obj); // 对象转化
        bird->foraging();
    } else if (typeid(*obj) == typeid(Plane)) {
        Plane* plane = dynamic_cast<Plane *>(obj);
        plane->carry();
    }

    obj->land();
}

int main(){
    Bird *b = new Bird();
    doSomething(b);
    delete b;
    Plane* p = new Plane();
    doSomething(p);
    delete p;
    return 0;
}