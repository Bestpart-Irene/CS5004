public class LinkedListOfStrings implements IListOfStrings {
    
    // 内部节点类
    private class Node {
        String data;
        Node next;
        
        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public LinkedListOfStrings() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void add(String item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    @Override
    public String get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    // 1. isEmpty
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 2. size
    @Override
    public int size() {
        return size;
    }

    // 3. contains
    @Override
    public boolean contains(String s) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(s)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // 4. containsAll
    @Override
    public boolean containsAll(IListOfStrings otherList) {
        // 遍历 otherList 中的每一个元素，检查是否存在于当前列表中
        for (int i = 0; i < otherList.size(); i++) {
            if (!this.contains(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    // 5. filterLargerThan
    @Override
    public IListOfStrings filterLargerThan(int maxLength) {
        LinkedListOfStrings newList = new LinkedListOfStrings();
        Node current = head;
        while (current != null) {
            // 只保留长度 <= maxLength 的字符串（即移除大于的）
            if (current.data.length() <= maxLength) {
                newList.add(current.data);
            }
            current = current.next;
        }
        return newList;
    }

    // 6. hasDuplicates
    @Override
    public boolean hasDuplicates() {
        Node current = head;
        while (current != null) {
            Node runner = current.next;
            while (runner != null) {
                if (current.data.equals(runner.data)) {
                    return true;
                }
                runner = runner.next;
            }
            current = current.next;
        }
        return false;
    }

    // 7. removeDuplicates
    @Override
    public IListOfStrings removeDuplicates() {
        LinkedListOfStrings newList = new LinkedListOfStrings();
        Node current = head;
        while (current != null) {
            // 如果新列表中还没有这个元素，就添加进去
            if (!newList.contains(current.data)) {
                newList.add(current.data);
            }
            current = current.next;
        }
        return newList;
    }
}