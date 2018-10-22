package com.nocomment.bus;

//graf, plan grada
public class GraphAsLists {
    protected LinkedNode start;

    protected LinkedNode findNode(int pod)
    {
        LinkedNode ptr = start;
        while(ptr != null && ptr.node!= pod)
            ptr = ptr.next;
        return ptr;
    }

    protected LinkedNode findNode(String pod)
    {
        LinkedNode ptr = start;
        while(ptr != null && !ptr.naziv.equals(pod))
            ptr = ptr.next;
        return ptr;
    }

    public Edge findEdge(int a, int b)
    {
        LinkedNode pa = findNode(a);
        LinkedNode pb = findNode(b);

        if(pa == null || pb == null) return null;
        Edge ptr = pa.adj;
        while(ptr != null && ptr.dest != pb)
            ptr=ptr.link;
        return ptr;

    }

    public boolean InsertNode (int pod)
    {
        LinkedNode newNode = new LinkedNode();
        if(newNode == null)
            return false;
        newNode.node=pod;
        newNode.adj= null;
        newNode.next= start;
        start= newNode;
        return true;

    }

    public boolean InsertNode (int pod,String ime)
    {
        LinkedNode newNode = new LinkedNode();
        if(newNode == null)
            return false;
        newNode.node=pod;
        newNode.adj= null;
        newNode.naziv=ime;
        newNode.next= start;
        start= newNode;
        return true;

    }

    public boolean deleteNode (int pod)
    {
        LinkedNode ptr;
        if(start == null) return false;
        if(start.node == pod)
        {
            ptr=start;
            Edge pot = start.adj;
            while(pot != null)
            {
                Edge tpot = pot.link;
                pot= tpot;
            }
            deleteEdgeToNode(start);
            start = start.next;
            return true;
        }
        else
        {
            ptr=start.next;
            LinkedNode par=start;
            while(ptr != null && ptr.node != pod)
            {
                par=ptr;
                ptr=ptr.next;
            }
            if(ptr == null) return false;
            par.next = ptr.next;
            Edge pot = ptr.adj;
            while(pot != null)
            {
                Edge tpot = pot.link;
                pot = tpot;
            }
            deleteEdgeToNode(ptr);
            return true;
        }

    }

    protected void deleteEdgeToNode (LinkedNode ptr)
    {
        LinkedNode pa= start;
        while(pa != null)
        {
            Edge pot = pa.adj;
            Edge prev = pa.adj;
            while(pot != null)
            {
                if(pot.dest == ptr)
                {

                    if(pa.adj == pot)
                    {
                        pa.adj = pot.link;
                        pot = pa.adj;
                    }
                    else
                    {
                        prev.link = pot.link;
                        pot = prev.link;

                    }
                }
                else
                {
                    prev =pot;
                    pot=pot.link;
                }
            }
            pa= pa.next;
        }
    }


    public boolean InsertEdge(int a, int b){
        LinkedNode pa=findNode(a);
        LinkedNode pb=findNode(b);
        if(pa == null || pb == null)
            return false;
        Edge ptr= new Edge();
        if(ptr == null)
            return false;
        ptr.dest=pb;
        ptr.link=pa.adj;
        pa.adj=ptr;
        return true;

    }

    public boolean InsertEdgeSVremenom(int a, int b,Vreme t){
        LinkedNode pa=findNode(a);
        LinkedNode pb=findNode(b);
        if(pa == null || pb == null)
            return false;
        Edge ptr= new Edge();
        ptr.vremePuta=t;
        if(ptr == null)
            return false;
        ptr.dest=pb;
        ptr.link=pa.adj;
        pa.adj=ptr;
        return true;

    }

    public boolean deleteEdge (int a, int b){
        Edge pot=findEdge(a,b);
        if(pot == null)
            return false;
        LinkedNode pa=findNode(a);
        if(pot == pa.adj)
        {
            pa.adj=pot.link;
            return true;
        }
        Edge tpot=pa.adj;
        while(tpot.link!=pot)
            tpot=tpot.link;
        tpot.link=pot.link;
        return true;
    }


	/*	public int topologicalOrderTraversal()
	{
		int retVal = 0; // broj obidjenih cvorova
		//obradjivanje ulaznog stepena za svaki cvor
		LinkedNode ptr;
		LinkedNode ptr2;
		ptr= start;
		while(ptr != NULL)
		{
			Edge pot ptr.adj;
			while(pot != null)
			{
				Edge pot = ptr.adj;
				while(pot != null)
				{
					pot.dest.status += 1;
					pot = pot.link;

				}

			}
			//sve izvorne potege smestiti u red
			QueueAsArray que;
			ptr = start;
			while(ptr != null)
			{
				if(ptr.status == 0)que.enqueue(ptr);
				ptr = ptr.next;

			}
			while(!que.IsEmpty())
			{
				ptr = que.dequeue();
				Visit(ptr);
				retVal += 1;
				Edge pot = ptr.adj;
				while(pot != null){
					pot.dest.status -= 1;
					if(pot.dest.status == 0)
						que.enqueue(pot.dest);
					pot= pot.link;
				}
			}
		}
		return retVal;

	}
*/
/*	public int breadthTrav(int a)//po sirini
	{
		int retVal=0;
		LinkedNode ptr = start;
		QueueAsArray = que;
		while (ptr != null){
			ptr.status = 1;
			ptr=ptr.next;
		}
		LinkedNode pa = findNode(a);
		if(pa == null)
			return;
		que.enquue(ptr);
		ptr.status=2;
		while(!que.isEmpty()){
			ptr=(LinkedNode)que.dequeue();
			ptr.status=3;
			Visit(ptr);
			retVal+=1;
			Edge pot=ptr.adj;
			while(pot != null){
				if(pot.dest.status == 1){
					pot.dest.status = 2;
					que.enqueue(pot.dest);
				}
			}
		}
		return retVal;
	}*/

	/*
	public int depthTrav(int a)//obilazak po dubini
	{
		int retVal = 0;
		LinkedNode ptr = start;
		StackAsArray stack;
		while(ptr != null){
			ptr.status = 1;
			ptr = ptr.next;
			}
		LinkedNode pa = findNode(a);
		if(pa == null)
			return;
		stack.push(ptr);
		ptr.status = 2;
		while(!stack.isEmpty()){
			ptr=(LinkedNode)track.pop();
			ptr.status = 3;
			Visit(ptr);
			retVal+=1;
			Edge pot=pot.adj;
			while(pot != null){
				if(pot.dest.status==1){
					pot.dest.status=2;
					stack.push(opt.dest);

				}
			}
		}
		return retVal;

	}//kraj deothTrav

*/
}
